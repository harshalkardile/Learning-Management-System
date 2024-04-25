package com.lmsdemo1.demo1.Homecontroller;

import com.lmsdemo1.demo1.Repository.RegistrationRepo;
import com.lmsdemo1.demo1.model.ClassworkT;
import com.lmsdemo1.demo1.Repository.ClassworkRepository;
import com.lmsdemo1.demo1.Repository.RegTeRepository;
import com.lmsdemo1.demo1.model.RegisterSt;
import com.lmsdemo1.demo1.service.ClassworkService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
//@RequestMapping("Classwork")
public class ClassworkController {


    @Autowired
    private ClassworkRepository classworkRepository;
    @Autowired
    private RegTeRepository reTeRepository;

    private final ClassworkService classworkService;
    @Autowired
    public ClassworkController(ClassworkService classworkService) {

        this.classworkService = classworkService;
    }
    @Autowired
    RegistrationRepo registrationRep;



    @RequestMapping("/deskclasswork")
    public String CLassworkFormdesk(Model model, HttpSession session, Authentication authentication){
        model.addAttribute("classworkt",new ClassworkT());

        RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;
        session.setAttribute("userfirstname",registerSt.getRoles().getRoleName());

        return "/LoginModule/deskclasswork.html";
    }


    @RequestMapping(value = "/saveMsgC", method = POST)
    public String saveMessage(@Valid @ModelAttribute("classworkt") ClassworkT classworkT, Errors errors){
        if (errors.hasErrors()){
            log.error("classwork for validation failed due to :"+ errors);
            return "/LoginModule/classwork.html";
        }
        classworkService.createClasswork(classworkT);
        return "redirect:/displayClasswork";
    }

    @RequestMapping("/ClassworkformT")
    public String CLassworkForm(Model model){
        model.addAttribute("classworkt",new ClassworkT());
        return "LoginModule/classwork.html";
    }



    @RequestMapping("/displayClasswork")
    public ModelAndView displayStudents(Model model){
        List<ClassworkT> classworkDisp = classworkService.findAllClasswork();
        ModelAndView modelAndView = new ModelAndView("Dashboards/LMS Student dashboard/pages/tables/classwork.html");
        modelAndView.addObject("classworkDisp",classworkDisp);
        return modelAndView;
    }

    @RequestMapping("/deleteClasswork")
    public ModelAndView deleteClasswork(Model model, @RequestParam int id) {
        Optional<ClassworkT> classworkT = classworkRepository.findById(id);
//        for(RegisterTe registerTe : classworkT.get().getRegisterTe()){
//            registerTe.setClassworkT(null);
//            reTeRepository.save(registerTe);
//        }
        classworkRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/displayClasswork");
        return modelAndView;
    }

}

