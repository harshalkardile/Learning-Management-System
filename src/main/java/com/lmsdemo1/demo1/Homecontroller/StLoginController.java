//package com.lmsdemo1.demo1.controller;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//
//
//
//@Slf4j
//@Controller
//public class StLoginController {
//
//    @RequestMapping(value ="/Login",method = { RequestMethod.GET, RequestMethod.POST })
////    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
////    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
//
//
////    public ModelAndView saveMessage(@RequestParam String fname, @RequestParam String lname, @RequestParam String id,
////                                    @RequestParam String email, @RequestParam String rp, @RequestParam String mob ){
//    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
//                                   @RequestParam(value = "logout", required = false) String logout,Model model) {
//        String errorMessge = null;
//        if(error != null) {
//            errorMessge = "Username or Password is incorrect !!";
//        }
//        if(logout != null) {
//            errorMessge = "You have been successfully logged out !!";
//        }
//        model.addAttribute("errorMessge", errorMessge);
//
//        return "LoginModule/Login.html";
//
//    }
//
//    @RequestMapping(value="/Logout", method = RequestMethod.GET)
//    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null)
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//
//        return "redirect:/Login?logout=true";
//    }
//}
