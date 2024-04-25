package com.lmsdemo1.demo1.service;

import com.lmsdemo1.demo1.Repository.ClassworkRepository;
import com.lmsdemo1.demo1.model.ClassworkT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ClassworkService {

    @Autowired
    private ClassworkRepository classworkRepository;

    public ClassworkService(){

        System.out.println("Classwork service has been initialized for the Teacher");
    }
    public boolean createClasswork(ClassworkT classworkT) {
        boolean isSaved = false;
//        classworkT.setCreatedBy(LmsConstants.ANONYMOUS);
        classworkT.setCreatedAt(LocalDateTime.now());

        String url=classworkT.getLink();
        int endIndex = url.lastIndexOf("/");
        String cutUrl = url.substring(0, endIndex)+"/preview";
        classworkT.setLink(cutUrl);

        ClassworkT savedClasswork = classworkRepository.save(classworkT);
        if (null != savedClasswork && savedClasswork.getCid() > 0) {
            isSaved = true;
        }
        return isSaved;
    }


    public List<ClassworkT> findAllClasswork() {
//        List<RegisterSt> studentDisp = reStRepository.findByCreatedBy(LmsConstants.ANONYMOUS);
        List<ClassworkT> classworkDisp = classworkRepository.findAll();
        return classworkDisp;
    }

}
