package com.lmsdemo1.demo1.Homecontroller;

import lombok.extern.slf4j.Slf4j;
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
public class TeDashboardController {

    @RequestMapping("/TeacherD")
//    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public String displayDashboard(Model model,Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        //throw new RuntimeException("It's been a bad day!!");
        return "/Dashboards/LMS Teacher dashboard/index.html";
    }

    @RequestMapping("/TeacherDesk")
//    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public String displayDashboardT(Model model,Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        //throw new RuntimeException("It's been a bad day!!");
        return "/Dashboards/LMS Teacher dashboard/deskteacher.html";
    }

}
