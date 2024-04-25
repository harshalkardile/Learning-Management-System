package com.lmsdemo1.demo1.service;

import com.lmsdemo1.demo1.constants.AddEventConstant;
import com.lmsdemo1.demo1.model.RegisterTe;
import com.lmsdemo1.demo1.Repository.RegTeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
//@RequestScope
public class RegTeService {
    @Autowired
    private RegTeRepository reTeRepository;

    public RegTeService(){

        System.out.println("Registration service has been initialized for the Teacher");
    }
    public boolean saveMessageDetails(RegisterTe registerTe) {
        boolean isSaved = false;
//        registerTe.setCreatedBy(LmsConstants.ANONYMOUS);
//        registerTe.setCreatedAt(LocalDateTime.now());
        RegisterTe savedTeacher = reTeRepository.save(registerTe);
        if (null != savedTeacher && savedTeacher.getIdT() > 0) {
            isSaved = true;
        }
        return isSaved;
    }
    public List<RegisterTe> findAllTeachers(){
        List<RegisterTe> teacherDisp=reTeRepository.findByCreatedBy(AddEventConstant.ANONYMOUS);
        return teacherDisp;
    }
}
