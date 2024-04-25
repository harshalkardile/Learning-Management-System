package com.lmsdemo1.demo1.Homecontroller;


import com.lmsdemo1.demo1.Repository.RegistrationRepo;
import com.lmsdemo1.demo1.model.AssesmentT;
import com.lmsdemo1.demo1.Repository.AssesmentRepository;
import com.lmsdemo1.demo1.model.RegisterSt;
import com.lmsdemo1.demo1.service.AssesmentService;
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

@Controller
@Slf4j
public class AssesmentController{

    @Autowired
    private AssesmentRepository assesmentRepository;
    @Autowired
    private final AssesmentService assesmentService;

    @Autowired
    public AssesmentController(AssesmentService assesmentService) {

        this.assesmentService = assesmentService;
    }

    @Autowired
    RegistrationRepo registrationRep;

    @RequestMapping("/deskassesments")
    public String deskassesments(Model model, HttpSession session, Authentication authentication){
//        model.addAttribute("assesmentt",new AssesmentT());
        RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;
        session.setAttribute("userfirstname",registerSt.getRoles().getRoleName());

        return "/LMS/Assignments/deskassignments.html";
    }




    @RequestMapping(value = "/saveA", method = POST)
    public String saveMessage(@Valid @ModelAttribute("assesmentt") AssesmentT assesmentT, Errors errors){
        if (errors.hasErrors()){
            log.error("classwork for validation failed due to :"+ errors);
            return "LoginModule/Assesment.html";
        }
        assesmentService.createAssesment(assesmentT);
        return "redirect:/displayAssesments";
    }

    @RequestMapping("/Assesmentform")
    public String AssesmentForm(Model model){
        model.addAttribute("assesmentt",new AssesmentT());
        return "/LoginModule/Assesment.html";
    }


    @RequestMapping("/displayAssesments")
    public ModelAndView displayStudents(Model model){
        List<AssesmentT> assesmentDisp = assesmentService.findAllAssesments();
        ModelAndView modelAndView = new ModelAndView("Dashboards/LMS Student dashboard/pages/tables/assesment.html");
        modelAndView.addObject("assesmentDisp",assesmentDisp);
        return modelAndView;
    }

    @RequestMapping("/deleteAssessment")
    public ModelAndView deleteAssesment(Model model, @RequestParam int id) {
        Optional<AssesmentT> assesmentT = assesmentRepository.findById(id);
//        for(RegisterTe registerTe : classworkT.get().getRegisterTe()){
//            registerTe.setClassworkT(null);
//            reTeRepository.save(registerTe);
//        }
        assesmentRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/displayAssesments");
        return modelAndView;
    }
}
