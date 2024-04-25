package com.lmsdemo1.demo1.service;

import com.lmsdemo1.demo1.constants.AddEventConstant;
import com.lmsdemo1.demo1.model.ClassworkS;
import com.lmsdemo1.demo1.Repository.ClassworkRepositoryS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ClassworkServiceS {
    @Autowired
    private ClassworkRepositoryS classworkRepositoryS;

    public ClassworkServiceS(){

        System.out.println("Classwork service has been initialized for the Student");
    }

    public boolean createClassworkS(ClassworkS classworkS) {
        boolean isSaved = false;
        classworkS.setCreatedBy(AddEventConstant.ANONYMOUS);
        classworkS.setCreatedAt(LocalDateTime.now());

        ClassworkS savedClasswork = classworkRepositoryS.save(classworkS);
        if (null != savedClasswork && savedClasswork.getCid() > 0) {
            isSaved = true;
        }
        return isSaved;
    }
}
