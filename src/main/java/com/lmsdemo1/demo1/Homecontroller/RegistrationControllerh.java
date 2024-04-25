package com.lmsdemo1.demo1.Homecontroller;


import com.lmsdemo1.demo1.model.RegisterSth;
import com.lmsdemo1.demo1.Repository.FeedbackRepository;
import com.lmsdemo1.demo1.Repository.ReStRepository;
import com.lmsdemo1.demo1.Repository.RegTeRepository;
import com.lmsdemo1.demo1.service.RegStServiceh;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RegistrationControllerh {
//    private static Logger log= LoggerFactory.getLogger(RegistrationController.class);


    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private ReStRepository regStRepository;

    @Autowired
    private RegTeRepository reTeRepository;

    private final RegStServiceh regStService;

    @Autowired
    public RegistrationControllerh(RegStServiceh regStService) {

        this.regStService = regStService;
    }

    @RequestMapping("/Registration")
    public String RegPage(Model model){
        model.addAttribute("registerst",new RegisterSth());
        return "/LoginModule/regestration.html";
    }


//@PreAuthorize("hasAuthority('ROLE_STUDENT')")
    @RequestMapping(value = "/saveMsg", method = POST)
    public String createUser(@Valid @ModelAttribute("registerst") RegisterSth registerSth, Errors errors){
        if (errors.hasErrors()){
            log.error("Registration for validation failed due to :"+ errors);
            return "/LoginModule/regestration.html";
        }
        regStService.createNewStudent(registerSth);
        return "redirect:/Registration";
    }

    @RequestMapping("/displayStudents")
    public ModelAndView displayStudents(Model model){
        List<RegisterSth> studentDisp = regStService.findAllStudents();
        ModelAndView modelAndView = new ModelAndView("Dashboards/LMS Student dashboard/pages/tables/basic-table.html");
        modelAndView.addObject("studentDisp",studentDisp);
        return modelAndView;
    }

    @RequestMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int id) {
        Optional<RegisterSth> registerSth = regStRepository.findById(id);
//        for(RegisterSt registerSt : feedbackSt.get().getRegisterSt()){
//            registerSt.setRegisterSt(null);
//            feedbackRepository.save(feedbackSt);
//        }


        regStRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/displayStudents");
        return modelAndView;
    }
}

