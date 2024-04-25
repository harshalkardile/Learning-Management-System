package com.lmsdemo1.demo1.Homecontroller;


import com.lmsdemo1.demo1.model.ClassworkS;
import com.lmsdemo1.demo1.service.ClassworkServiceS;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
//@RequestMapping("ClassworkS")
public class ClassworkControllerS {


    private final ClassworkServiceS classworkServiceS;

    @Autowired
    public ClassworkControllerS(ClassworkServiceS classworkServiceS) {

        this.classworkServiceS = classworkServiceS;
    }

    @RequestMapping("/ClassworkformS")
    public String CLassworkFormS(Model model){
        model.addAttribute("classworks",new ClassworkS());
        return "LoginModule/classworks.html";
    }

    @RequestMapping(value = "/saveMsgCS", method = POST)
    public String saveMessage(@Valid @ModelAttribute("classworks") ClassworkS classworkS, Errors errors){
        if (errors.hasErrors()){
            log.error("classwork for validation failed due to :"+ errors);
            return "LoginModule/classworks.html";
        }
        classworkServiceS.createClassworkS(classworkS);
        return "redirect:/ClassworkformS";
    }
}
