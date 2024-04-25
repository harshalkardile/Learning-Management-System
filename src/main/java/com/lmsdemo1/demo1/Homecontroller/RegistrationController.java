package com.lmsdemo1.demo1.Homecontroller;




import com.lmsdemo1.demo1.model.AddEvent;
import com.lmsdemo1.demo1.model.Coursecard;
import com.lmsdemo1.demo1.model.RegisterSt;
import com.lmsdemo1.demo1.model.Roles;
import com.lmsdemo1.demo1.service.RegStService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
@Slf4j
@Controller
@RequestMapping("public")
public class RegistrationController {
//    private static Logger log= LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    RegStService regStService;

//    public RegistrationController(RegStService regStService) {
//
//        this.regStService = regStService;
//    }

    @RequestMapping(value="/Registration",method = {RequestMethod.GET})
    public String RegPage(Model model){
        model.addAttribute("registerst",new RegisterSt());
        return "/LMS/LoginModule/regestration.html";
    }

//    @RequestMapping(value = "/saveMsg", method = POST)
//    public ModelAndView saveMessage(@RequestParam String fname, @RequestParam String lname, @RequestParam String id,
//                                    @RequestParam String email, @RequestParam String rp, @RequestParam String mob ){
//
//        log.info("FName: " + fname);
//        log.info("LName: " + lname);
//        log.info("StudentID: " + id);
//        log.info("EmailID: " + email);
//        log.info("Passwords: " + rp);
//        log.info("Mobile: " + mob);
//        return new ModelAndView(("redirect:/Registration"));
//    }




        @RequestMapping(value = "/createUser", method ={RequestMethod.POST})
        public String createUser(@Valid @ModelAttribute("registerst") RegisterSt registerSt, Errors errors){
            if (errors.hasErrors()){

                log.error("Registration for validation failed due to :"+ errors);
                return "/LMS/LoginModule/regestration.html";

            }
            boolean isSaved = regStService.createNewPerson(registerSt);


            if(isSaved){
                return "redirect:/Login?Registration=true";
            }
            else {
                return "/LMS/LoginModule/regestration.html";
            }
        }



          //  regStService.saveMessageDetails(registerSt);
           // return "redirect:/Login?Registration=true";


    @RequestMapping("/Detial")
    public ModelAndView displayStudent(Model model){
        List<RegisterSt> studentMsgs = regStService.findStudentdetailStatus();
        ModelAndView  modelAndView = new ModelAndView("/LMS/studentdetial.html");
        modelAndView.addObject("studentMsgs",studentMsgs);
        model.addAttribute("std",new RegisterSt());
        return modelAndView;
    }

    @RequestMapping("/TeacherDetial")
    public ModelAndView displayteacher(Model model){
        List<RegisterSt> studentMsgs = regStService.findTeacherdetailStatus();
        ModelAndView  modelAndView = new ModelAndView("/LMS/TeacherDetail.html");
        modelAndView.addObject("teacherMsgs",studentMsgs);
        model.addAttribute("teach",new RegisterSt());
        return modelAndView;
    }




    //    @RequestMapping("/displayregistrationMessages")
    //    public ModelAndView displayMessages(Model model) {
    //        List<AddEvent> eventMsgs = regStService.findMsgsWithOpenStatus();
    //        ModelAndView modelAndView = new ModelAndView("/LMS/classroom/eventmessages.html");
    //        modelAndView.addObject("eventMsgs",eventMsgs);
    //        return modelAndView;
    //    }
    //
    //
    //    @RequestMapping(value = "/closeregistration",method = GET)
    //    public String closeMsg(@RequestParam int id) {
    //        regStService.updateMsgStatus(id);
    //        return "redirect:/displayaddeventMessages";
    //    }

    @RequestMapping(value = "/addstudent",method ={RequestMethod.POST})
    public String addMsg(@Valid @ModelAttribute("std") RegisterSt registerSt, Errors errors)
    {
        if (errors.hasErrors()){

            log.error("adding student failed due to :"+ errors);
            return "/LMS/studentdetial.html";

        }
        boolean isSaved = regStService.Addstudents(registerSt);


        if(isSaved){
            return "redirect:/public/Detial";
        }
        else {
            return "/LMS/studentdetial.html";
        }
       // return "redirect:/public/Detial";
    }


    @RequestMapping(value = "/addteacher",method ={RequestMethod.POST})
    public String addteacherMsg(@Valid @ModelAttribute("teach") RegisterSt registerSt, Errors errors)
    {
        if (errors.hasErrors()){

            log.error("adding teacher failed due to :"+ errors);
            return "/LMS/TeacherDetail.html";

        }
        boolean isSaved = regStService.Addteacher(registerSt);


        if(isSaved){
            return "redirect:/public/TeacherDetial";
        }
        else {
            return "/LMS/TeacherDetail.html";
        }
        // return "redirect:/public/Detial";
    }


    @RequestMapping(value="/deletestudent/{id}",method={RequestMethod.GET})
 public String deletestudent(@PathVariable int id,HttpSession session ){
//        if (errors.hasErrors()){
//
//            log.error("Delete Operation  failed due to :"+ errors);
//            return "/public/Detial";
//
//        }
        regStService.deletestud(id);
        //session.setAttribute("sms","Delete Successfully");
        return "redirect:/public/Detial";


 }


    @RequestMapping(value="/deleteteacher/{id}",method={RequestMethod.GET})
    public String deleteteacher(@PathVariable int id,HttpSession session ){

        regStService.deletestud(id);
        //session.setAttribute("sms","Delete Successfully");
        return "redirect:/public/TeacherDetial";
    }



    @RequestMapping(value={"/updatestudent/{id}"},method = {RequestMethod.GET})
    public String editcard(@PathVariable int id ,Model model){

        System.out.println("/n/n/n/n/n/n"+id);
//        ModelAndView modelAndView=new ModelAndView("/LMS/Courses/coursenav.html");
        // Optional<Coursecard> list=coursecardService.editform(id);
        RegisterSt registerSt=regStService.Infoform(id);

//        System.out.println(registerSt);
//Coursecard pro= list.get();
        model.addAttribute("info",registerSt);
//modelAndView.addObject("edited",coursecard);



        return "/LMS/studentupdateform.html";



    }

    @RequestMapping(value = {"/updatedstudent"} ,method = {RequestMethod.POST})
    public String updateeditform(@ModelAttribute("info") RegisterSt registerSt, HttpSession session) throws IOException {

        System.out.println(registerSt);
//        registerSt.setRoles(new Roles(2,"STUDENT"));
               regStService.updatestudentform(registerSt);



            session.setAttribute("msg","The User has successfully  Added" );

            return "redirect:/public/Detial";



    }








    @RequestMapping(value={"/updateteacher/{id}"},method = {RequestMethod.GET})
    public String editteacher(@PathVariable int id ,Model model){

        System.out.println("/n/n/n/n/n/n"+id);
//        ModelAndView modelAndView=new ModelAndView("/LMS/Courses/coursenav.html");
        // Optional<Coursecard> list=coursecardService.editform(id);
        RegisterSt registerSt=regStService.Infoform(id);

//        System.out.println(registerSt);
//Coursecard pro= list.get();
        model.addAttribute("infot",registerSt);
//modelAndView.addObject("edited",coursecard);



        return "/LMS/teacherupdateform.html";



    }

    @RequestMapping(value = {"/updatedteacher"} ,method = {RequestMethod.POST})
    public String updateteacherform(@ModelAttribute("infot") RegisterSt registerSt, HttpSession session) throws IOException {

        System.out.println(registerSt);
//        registerSt.setRoles(new Roles(2,"STUDENT"));
        regStService.updateteacherform(registerSt);



        session.setAttribute("msg","The User has successfully  Added" );

        return "redirect:/public/TeacherDetial";



    }












}







