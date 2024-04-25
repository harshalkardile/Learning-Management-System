package com.lmsdemo1.demo1.service;

import com.lmsdemo1.demo1.model.RegisterSth;
import com.lmsdemo1.demo1.Repository.ReStRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RegStServiceh {

    @Autowired
    private ReStRepository reStRepository;

    public RegStServiceh() {

        System.out.println("Registration service has been initialized for the Student");
    }

    //    private static Logger log= LoggerFactory.getLogger(RegStService.class);
    public boolean createNewStudent(RegisterSth registerSth) {
        boolean isSaved = false;
//        registerSt.setCreatedBy(LmsConstants.ANONYMOUS);
//        registerSt.setCreatedAt(LocalDateTime.now());

        RegisterSth savedStudent = reStRepository.save(registerSth);
        if (null != savedStudent && savedStudent.getId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<RegisterSth> findAllStudents() {
//        List<RegisterSt> studentDisp = reStRepository.findByCreatedBy(LmsConstants.ANONYMOUS);
        List<RegisterSth> studentDisp = reStRepository.findAll();
    return studentDisp;
    }
}