package com.lmsdemo1.demo1.Homecontroller;

import com.lmsdemo1.demo1.Repository.RegistrationRepo;
import com.lmsdemo1.demo1.model.RegisterSt;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@Controller
public class StDashboardController {

    @Autowired
    RegistrationRepo registrationRep;



    @RequestMapping("/StudentD")
//    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public String displayDashboard(Model model,Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
//        throw new RuntimeException("It's been a bad day!!");
        return "/Dashboards/LMS Student dashboard/index.html";
    }
    @RequestMapping("/StudentDesk")
//    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public String displayDashboardS(Model model, Authentication authentication, HttpSession session) {
        model.addAttribute("username", authentication.getName());

        RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;
        session.setAttribute("userfirstname",registerSt.getRoles().getRoleName());

        model.addAttribute("roles", authentication.getAuthorities().toString());
//        throw new RuntimeException("It's been a bad day!!");
        return "/Dashboards/LMS Student dashboard/deskstudent.html";
    }

}