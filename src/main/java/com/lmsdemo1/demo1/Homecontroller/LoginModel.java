package com.lmsdemo1.demo1.Homecontroller;


import com.lmsdemo1.demo1.Repository.Classroomrepo;
import com.lmsdemo1.demo1.Repository.RegistrationRepo;
import com.lmsdemo1.demo1.model.AddBookModel;
import com.lmsdemo1.demo1.model.Classroom;
import com.lmsdemo1.demo1.model.RegisterSt;
import com.lmsdemo1.demo1.service.AddBookService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

//import static org.yaml.snakeyaml.TypeDescription.log;
@Slf4j
@Controller
public class LoginModel {


    private final AddBookService addBookService;

    @Autowired
    public LoginModel(AddBookService addBookService) {
        this.addBookService = addBookService;
    }

//    @Autowired
//    public void Addlibrary(AddBookService addBookService) {
//        this.addBookService = addBookService;
//    }


    @Autowired
    Classroomrepo classroomrepo;


    @Autowired
    RegistrationRepo registrationRepo;



    @RequestMapping(value={
            "/DLogin"
    })
    public String DLoginPage(){
        return "./LMS/Login/deskLogin.html";
    }
    @RequestMapping(value={
            "/classroom"
    })
    public String ClassroomPage(HttpSession session,Authentication authentication){


        RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;
        session.setAttribute("userfirstname",registerSt.getRoles().getRoleName());
        return "/LMS/classroom/classroompahe.html";
    }


    @RequestMapping(value={
            "/classroompage1"
    })
    public String Classpage1(){
        return "/LMS/classroom/classpathtable.html";
    }

    @RequestMapping(value={
            "/calenderjava"
    })
    public String Calenderpage1(){
        return "/LMS/animated-calendar-event-gc/index.html";
    }




    @RequestMapping(value={
            "/libraryembed"
    })
    public String Libraryembed(Model model){

        List<AddBookModel> list=addBookService.displaybook();
        model.addAttribute("addBookModel",list);
        return "/LMS/Library/indexforlibrary.html";
    }



    @RequestMapping(value={
            "/librarydesk"
    })
    public String Librarypage0(HttpSession session, Authentication authentication){

        String name=(String) session.getAttribute("userfirstname");
        session.setAttribute("username",name);

        RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;
        session.setAttribute("userfirstname",registerSt.getRoles().getRoleName());

        return "/LMS/Library/desklibrary.html";
    }

    @Autowired
    RegistrationRepo registrationRep;

    @RequestMapping(value={
            "/indexforstudent"
    })
    public String studentindex(HttpSession session, Authentication authentication){

//   RegisterSt name=(RegisterSt) session.getAttribute("userfirstname");
        RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;
        session.setAttribute("userfirstname",registerSt.getRoles().getRoleName());

//        session.setAttribute("userfirstname",name);
        return "/LMS/indexforstudent.html";
    }





    @RequestMapping(value={
            "/indexforteacher"
    })
    public String teacherindex(HttpSession session,Authentication authentication){

        RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;
        session.setAttribute("userfirstname",registerSt.getRoles().getRoleName());

        return "/LMS/indexforteacher.html";
    }






    @RequestMapping(value={
            "/displayclass"
    })
    public String displayclasses(Model model){

        List<Classroom> cr = classroomrepo.findAll();

        model.addAttribute("classes",cr);
        model.addAttribute("eazyClass",new Classroom());

        return "/LMS/classroom/classes.html";
    }



    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("eazyClass") Classroom cr) {
        classroomrepo.save(cr);
        ModelAndView modelAndView = new ModelAndView("redirect:/displayclass");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<Classroom> cr = classroomrepo.findById(id);
        for(RegisterSt registerSt: cr.get().getPersons()){
            registerSt.setClassroom(null);
            registrationRepo.save(registerSt);
        }
        classroomrepo.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/displayclass");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classid, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("/LMS/classroom/studentdisplay.html");
        Optional<Classroom> eazyClass = classroomrepo.findById(classid);
        modelAndView.addObject("eazyClass",eazyClass.get());
        modelAndView.addObject("person",new RegisterSt());
        session.setAttribute("eazyClass",eazyClass.get());
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") RegisterSt registerSt, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Classroom eazyClass = (Classroom) session.getAttribute("eazyClass");
        RegisterSt registerSt1 = registrationRepo.readByEmail(registerSt.getEmail());
        if(registerSt1==null || !(registerSt1.getId()>0)){
            modelAndView.setViewName("redirect:/displayStudents?classid="+eazyClass.getClassid()
                    +"&error=true");
            return modelAndView;
        }
        registerSt1.setClassroom(eazyClass);
        registrationRepo.save(registerSt1);
        eazyClass.getPersons().add(registerSt1);
        classroomrepo.save(eazyClass);
        modelAndView.setViewName("redirect:/displayStudents?classid="+eazyClass.getClassid());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int Id, HttpSession session) {
        Classroom eazyClass = (Classroom) session.getAttribute("eazyClass");
        Optional<RegisterSt> registerSt = registrationRepo.findById(Id);
        registerSt.get().setClassroom(null);
        eazyClass.getPersons().remove(registerSt.get());
        Classroom eazyClassSaved = classroomrepo.save(eazyClass);
        session.setAttribute("eazyClass",eazyClassSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/displayStudents?classid="+eazyClass.getClassid());
        return modelAndView;
    }



}


