package com.lmsdemo1.demo1.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class Webconfig  implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/coursedetail").setViewName("/LMS/Courses/coursedetail");
//        registry.addViewController("/addbook").setViewName("/LMS/Library/hexashop-1.0.0/assets/addbook.html");
    }
}
