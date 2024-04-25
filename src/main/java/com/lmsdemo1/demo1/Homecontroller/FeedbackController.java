package com.lmsdemo1.demo1.Homecontroller;

import com.lmsdemo1.demo1.Repository.RegistrationRepo;
import com.lmsdemo1.demo1.model.ClassworkT;
import com.lmsdemo1.demo1.model.FeedbackSt;
import com.lmsdemo1.demo1.model.RegisterSt;
import com.lmsdemo1.demo1.service.FeedbackService;
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

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
public class FeedbackController {

    @Autowired
    RegistrationRepo registrationRep;


    private final FeedbackService feedbackService;
    @Autowired
    public FeedbackController(FeedbackService feedbackService) {

        this.feedbackService = feedbackService;
    }

    @RequestMapping("/deskfeedback")
    public String FeedbackFormdesk(Model model, HttpSession session, Authentication authentication){
//        model.addAttribute("classworkt",new ClassworkT());

        RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;
        session.setAttribute("userfirstname",registerSt.getRoles().getRoleName());

        return "/LMS/deskfeedback.html";
    }


    @RequestMapping("/Feedback")
    public String FeedbackForm(Model model){
        model.addAttribute("feedbackst",new FeedbackSt());
        return "/LoginModule/Feedbackform.html";
    }

    @RequestMapping(value = "/saveMsgF", method = POST)
    public String saveMessage(@Valid @ModelAttribute("feedbackst") FeedbackSt feedbackSt, Errors errors,HttpSession session){
        if (errors.hasErrors()){

            log.error("feedback for validation failed due to :"+ errors);

            return "/LoginModule/Feedbackform.html";

        }

        feedbackService.saveMessageDetails(feedbackSt);
        session.setAttribute("msgs","FeedBack submitted SuccessFully");
        return "redirect:/Feedback";
    }


    @RequestMapping("/removefeedback")
    public String messageremove(Model model,HttpSession session){
        session.removeAttribute("msgs");
        model.addAttribute("feedbackst",new FeedbackSt());
        return "/LoginModule/Feedbackform.html";
    }




}
