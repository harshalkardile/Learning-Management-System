package com.lmsdemo1.demo1.service;


import com.lmsdemo1.demo1.Repository.RegistrationRepo;
import com.lmsdemo1.demo1.Repository.RolesRepo;
import com.lmsdemo1.demo1.constants.AddEventConstant;
import com.lmsdemo1.demo1.model.AddEvent;
import com.lmsdemo1.demo1.model.Coursecard;
import com.lmsdemo1.demo1.model.RegisterSt;
import com.lmsdemo1.demo1.model.Roles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@SessionScope
public class RegStService {


    @Autowired
    private RegistrationRepo registrationRepo;

    @Autowired
    private RolesRepo rolesrepo;

    @Autowired
    private PasswordEncoder passwordEncoder;



 String Name=AddEventConstant.STUDENT_ROLE;

//    private static Logger log= LoggerFactory.getLogger(RegStService.class);
    public  boolean createNewPerson(RegisterSt registerSt) {
        boolean isSaved=false;
        Roles role = rolesrepo.getByRoleName(Name);
        registerSt.setRoles(role);

        registerSt.setPwd(passwordEncoder.encode(registerSt.getPwd()));
        registerSt = registrationRepo.save(registerSt);
         if (null != registerSt && registerSt.getId()> 0)
        {
            isSaved = true;

        }

        //todo need to persist the data into the database table
       //log.info(registerSt.toString());
        return isSaved;
    }

        int r=2;
    public  List<RegisterSt> findStudentdetailStatus(){
        List<RegisterSt> studentMsgs = registrationRepo.findByRolesRoleId(r);
        return studentMsgs;
    }

    public  List<RegisterSt> findTeacherdetailStatus(){
        List<RegisterSt> teacherMsgs = registrationRepo.findByRolesRoleId(3);
        return teacherMsgs;
    }

    public boolean updateStudentdisplay(int contactId){

        boolean isUpdated = false;
        Optional<RegisterSt> contact = registrationRepo.findById(contactId);


//        contact.ifPresent(contact1 -> {
//           contact1 = contact.get();
//            // Assuming `deletebyId` is a method in the `RegisterSt` class to update the display status
//            registrationRepo.deleteById(contactId);
//            isUpdated = true;
//        });


        if (contact.isPresent()) {
            RegisterSt contactop = contact.get();
            registrationRepo.deleteById(contactId);
            isUpdated=true;
        }




//  RegisterSt  updatedContact = registrationRepo.save(contact.get());
//
//        if(null != updatedContact && updatedContact.getUpdatedBy()!=null) {
//            isUpdated = true;
//        }



     return isUpdated;


    }

public boolean Addstudents(RegisterSt registerSt) {

    boolean isSaved = false;
    Roles role = rolesrepo.getByRoleName(Name);
    registerSt.setRoles(role);

    registerSt.setPwd(passwordEncoder.encode(registerSt.getPwd()));
    registerSt = registrationRepo.save(registerSt);
    if (null != registerSt && registerSt.getId() > 0) {
        isSaved = true;

    }

    //todo need to persist the data into the database table
    //log.info(registerSt.toString());
    return isSaved;
}


    public boolean Addteacher(RegisterSt registerSt) {

        boolean isSaved = false;
        Roles role = rolesrepo.getByRoleName(AddEventConstant.TEACHER_ROLE);
        registerSt.setRoles(role);

        registerSt.setPwd(passwordEncoder.encode(registerSt.getPwd()));
        registerSt = registrationRepo.save(registerSt);
        if (null != registerSt && registerSt.getId() > 0) {
            isSaved = true;

        }

        //todo need to persist the data into the database table
        //log.info(registerSt.toString());
        return isSaved;
    }


    public void deletestud(int id)
    {

        Long count=registrationRepo.countById(id);

            if(count==null || count==0)
            {
                System.out.println("Count can Not Be Null"+count);

            }



        registrationRepo.deleteById(id);


    }




    public RegisterSt Infoform(Integer id){
        Optional<RegisterSt> optional=registrationRepo.findById(id);
        RegisterSt registerSt=null;
        if(optional.isPresent()){
            registerSt=optional.get();
        }
        else {
            throw new RuntimeException("**********Detial are not found::"+id);
        }
        return registerSt;

    }


    public Boolean updatestudentform(RegisterSt registerSt)
    {
        boolean isSaved = false;
        Roles roles=rolesrepo.getByRoleName(Name);
        registerSt.setRoles(roles);

        registerSt.setPwd(passwordEncoder.encode(registerSt.getPwd()));

        registerSt = registrationRepo.save(registerSt);

        if (null != registerSt && registerSt.getId() > 0) {
            isSaved = true;

        }

       return isSaved;
    }






    public Boolean updateteacherform(RegisterSt registerSt)
    {
        boolean isSaved = false;
        Roles roles=rolesrepo.getByRoleName(AddEventConstant.TEACHER_ROLE);
        registerSt.setRoles(roles);

        registerSt.setPwd(passwordEncoder.encode(registerSt.getPwd()));

        registerSt = registrationRepo.save(registerSt);

        if (null != registerSt && registerSt.getId() > 0) {
            isSaved = true;

        }

        return isSaved;
    }



}
