package com.lmsdemo1.demo1.Homecontroller;

import com.lmsdemo1.demo1.Repository.RegistrationRepo;
import com.lmsdemo1.demo1.model.RegisterSt;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class Dashboard {

    @Autowired
    RegistrationRepo registrationRep;

    @ExceptionHandler()
    @RequestMapping(value="/Admin")
    public String DashboardAdmin(Model model, Authentication authentication, HttpSession session)
    {

       RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;


       System.out.println(registerSt);
        model.addAttribute("username", registerSt.getFname());
        session.setAttribute("userfirstname",registerSt.getRoles().getRoleName());

        model.addAttribute("roles", authentication.getAuthorities().toString());

        session.setAttribute("LoggedInPerson",registerSt);
        //throw new RuntimeException("whole day wasted");
        return "/LMS/index.html";

    }












}
