package com.lmsdemo1.demo1.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ProjectSecurityConfig {
    /**
     * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
     * to move towards a component-based security configuration. It is recommended to create a bean
     * of type SecurityFilterChain for security related configurations.
     * @param
     * @return SecurityFilterChain
     * @throws Exception
     */
    //alternet method to disbale Httprequest in security





       @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        // Permit All Requests inside the Web Application
        http.headers()
                .frameOptions()
                .disable().and()
                .csrf().ignoringRequestMatchers("/public/**").ignoringRequestMatchers("/saveMsgT")
//            .ignoringRequestMatchers("/saveMsgC")
                   .ignoringRequestMatchers("/saveMsgF").and()

                .authorizeRequests()
               // .requestMatchers("/LMS/classroom/**").permitAll()
                .requestMatchers("/Login").permitAll()
                .requestMatchers("/Regestration").permitAll()
                .requestMatchers("/Registration").permitAll()
                .requestMatchers("/Admin").authenticated()
                .requestMatchers("displayProfile").authenticated()
                .requestMatchers("updateProfile").authenticated()
                .requestMatchers("/editedupdated").permitAll()
                .requestMatchers("../static/**").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/images/**").permitAll()
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/readbook/**").permitAll()
                .requestMatchers("/public/Detial").hasRole("ADMIN")
                .requestMatchers("/public/TeacherDetial").hasRole("ADMIN")
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/calenderjava").permitAll()
                .requestMatchers("/classroom/**").authenticated()
                .requestMatchers("/LMS/classroom/**").permitAll()
                .requestMatchers("/displayaddeventMessages").hasRole("ADMIN")
                .requestMatchers("/classroompage1").permitAll()
                .requestMatchers("/Library/hexashop-1.0.0/assets/css/**").permitAll()
                .requestMatchers("/coursecontain").permitAll()
                .requestMatchers("/coursespage").authenticated()
                .requestMatchers("/coursedetail").permitAll()
                .requestMatchers("/librarydesk").permitAll()
               .requestMatchers("/LMS/classroom/**").authenticated()
                .requestMatchers("/holidays/**").permitAll()
                .requestMatchers("/addbook").authenticated()
                .requestMatchers("/savebook").authenticated()
                .requestMatchers("/addevent").authenticated()
                .requestMatchers("/saveEvent").permitAll()

//Harshal Kardile Configuration  File Data Mentioned Below
           .requestMatchers(AntPathRequestMatcher.antMatcher("/StudentDashboard/**")).permitAll()
           .requestMatchers("/TeacherDashboard/**").permitAll()
           .requestMatchers(AntPathRequestMatcher.antMatcher("/RegistrationT")).permitAll()
           .requestMatchers(AntPathRequestMatcher.antMatcher("/Registration")).permitAll()
//           .requestMatchers(AntPathRequestMatcher.antMatcher("/style.css")).permitAll()
//           .requestMatchers(AntPathRequestMatcher.antMatcher("/Login")).permitAll()
//           .requestMatchers("/LoginT").permitAll()
           .requestMatchers("/Fileserror").permitAll()
           .requestMatchers(AntPathRequestMatcher.antMatcher("/StudentD")).permitAll()
           .requestMatchers("/TeacherD").permitAll()
//           .requestMatchers("/RegistrationT").permitAll()
           .requestMatchers("/Feedback").permitAll()
           .requestMatchers("/Classwork").permitAll()
//           .requestMatchers("/LogoutT").permitAll()
           .requestMatchers("/DisplayTab").permitAll()
           .requestMatchers("/displayStudents").permitAll()
           .requestMatchers("/displayTeachers").permitAll()
//            auth.requestMatchers("/saveMsgC").permitAll();
//           .requestMatchers(AntPathRequestMatcher.antMatcher("/Logout")).permitAll()
           .requestMatchers(AntPathRequestMatcher.antMatcher("/saveMsg")).permitAll()
           .requestMatchers(AntPathRequestMatcher.antMatcher("/saveMsgT")).permitAll()
           .requestMatchers(AntPathRequestMatcher.antMatcher("/saveMsgF")).permitAll()
//Harshal Kardile Configuration File Data Mention Above

                .and().formLogin().loginPage("/Login")
                .defaultSuccessUrl("/Admin")
                .failureUrl("/Login?error=true")
                .permitAll()
                .and().logout()
                .logoutSuccessUrl("/Login?logout=true")
//                .and().formLogin().loginPage("/LoginT").defaultSuccessUrl("/TeacherD")
//                .failureUrl("/LoginT?error=true")
//                .permitAll()
//                .and().logout()
//                .logoutSuccessUrl("/LoginT?logout=true")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll()
                .and().httpBasic();
//
//           http.csrf().ignoringRequestMatchers(PathRequest.toH2Console()).and()
//                           .authorizeRequests()
//                   .requestMatchers(PathRequest.toH2Console())
//                   .permitAll();



//        http.headers()
//                .frameOptions()
//                .disable().and()
//           .csrf().ignoringRequestMatchers("/LMS/classroom/**")
//                .disable().authorizeRequests()
//                .requestMatchers("/LMS/classroom/**").permitAll();



        return http.build();

    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/classroom/**", "/classroompage1").anyRequest();
//    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("NR")
//                .password("Naresh@2023")
//                .roles("USER")
//                .build();
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("Naresh@2023")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }




}
