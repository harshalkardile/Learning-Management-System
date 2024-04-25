package com.lmsdemo1.demo1.Homecontroller;


import com.lmsdemo1.demo1.Repository.RegistrationRepo;
import com.lmsdemo1.demo1.model.Address;
import com.lmsdemo1.demo1.model.Profile;
import com.lmsdemo1.demo1.model.RegisterSt;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
public class ProfileController {


    @Autowired
    RegistrationRepo registrationRepo;

@RequestMapping("/profileindex")
public String profileindex(){
       return "/LMS/ProfileIndex.html";
}

    @RequestMapping("/remove")
    public String profileindexremove(HttpSession session, SessionScope sessionScope){
    session.removeAttribute("msg");

        return "redirect:/displayProfile";
    }


    @RequestMapping("/displayProfile")
    public ModelAndView displayMessage(Model model, HttpSession session, Authentication authentication,SessionScope sessionScope){
        RegisterSt registerSt2=(RegisterSt)session.getAttribute("LoggedInPerson");
        RegisterSt registerSt= registrationRepo.readByEmail(authentication.getName()) ;
        Profile profile=new Profile();
        profile.setProfileid(registerSt.getId());
        System.out.println(registerSt.getFname()+"**********");
        profile.setName(registerSt.getFname());
        profile.setMobileNumber(registerSt.getMob());
        profile.setEmail(registerSt.getEmail());
        if(registerSt.getAddress() !=null && registerSt.getAddress().getAddressId()>0){
            profile.setAddress1(registerSt.getAddress().getAddress1());
            profile.setAddress2(registerSt.getAddress().getAddress2());
            profile.setCity(registerSt.getAddress().getCity());
            profile.setState(registerSt.getAddress().getState());
            profile.setZipCode(registerSt.getAddress().getZipCode());
        }
//        sessionScope.remove("msg");
        RegisterSt registerSt1= registrationRepo.readByEmail(authentication.getName()) ;
        session.setAttribute("userfirstname",registerSt1.getRoles().getRoleName());
//        session.setAttribute("msg","Address Updated Successfully");

        ModelAndView modelAndView=new ModelAndView("/LMS/Profile.html");
        modelAndView.addObject("profile",profile);
//        modelAndView.addObject("msg","Address Updated Successfully");

        return modelAndView;



    }



    @PostMapping(value = "/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors,
                                HttpSession session,Authentication authentication,Model model)
    {

        RegisterSt registerSt= registrationRepo.readByEmail(authentication.getName()) ;
        profile.setName(registerSt.getFname());

        if(errors.hasErrors()){
            return "/LMS/Profile.html";
        }
        //RegisterSt registerSt = (RegisterSt) session.getAttribute("LoggedInPerson");

//        RegisterSt registerSt= registrationRepo.readByEmail(authentication.getName()) ;

        registerSt.setFname(profile.getName());

        registerSt.setEmail(profile.getEmail());
        registerSt.setMob(profile.getMobileNumber());
        if(registerSt.getAddress() ==null || !(registerSt.getAddress().getAddressId()>0)){
            registerSt.setAddress(new Address());
        }
        registerSt.getAddress().setAddress1(profile.getAddress1());
        registerSt.getAddress().setAddress2(profile.getAddress2());
        registerSt.getAddress().setCity(profile.getCity());
        registerSt.getAddress().setState(profile.getState());
        registerSt.getAddress().setZipCode(profile.getZipCode());
        RegisterSt savedPerson = registrationRepo.save(registerSt);
        session.setAttribute("loggedInPerson", savedPerson);
        session.setAttribute("msg", "Your Profile Updated Successfully");
//        model.addAttribute("showMessage", true);

//model.addAttribute("mass","Your Profile Updated Successfully");
        return "redirect:/displayProfile";

    }





}
