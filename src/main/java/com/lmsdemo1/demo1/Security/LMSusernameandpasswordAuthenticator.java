package com.lmsdemo1.demo1.Security;

import com.lmsdemo1.demo1.Repository.RegistrationRepo;
import com.lmsdemo1.demo1.model.RegisterSt;
import com.lmsdemo1.demo1.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LMSusernameandpasswordAuthenticator implements AuthenticationProvider {

    @Autowired
    private RegistrationRepo regRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {


        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        RegisterSt registerSt = regRepo.readByEmail(email);
        String ios=registerSt.getRoles().getRoleName();

         if(null != registerSt && registerSt.getId() >0 &&
                 passwordEncoder.matches(pwd,registerSt.getPwd())){



            return new UsernamePasswordAuthenticationToken(
                    email, null, getGrantedAuthorities(registerSt.getRoles()));
        }

         else{
            throw new BadCredentialsException("Invalid credentials!");
        }



    }

    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+roles.getRoleName()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}