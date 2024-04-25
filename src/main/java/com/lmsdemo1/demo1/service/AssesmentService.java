package com.lmsdemo1.demo1.service;


import com.lmsdemo1.demo1.model.AssesmentT;
import com.lmsdemo1.demo1.Repository.AssesmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AssesmentService {

    public AssesmentService(){
        System.out.println("Assesment service has been initialized for the Teacher");
    }

    @Autowired
    private AssesmentRepository assesmentRepository;

    public boolean createAssesment(AssesmentT assesmentT) {
        boolean isSaved = false;
//        classworkT.setCreatedBy(LmsConstants.ANONYMOUS);
//        classworkT.setCreatedAt(LocalDateTime.now());

        AssesmentT savedAssesment = assesmentRepository.save(assesmentT);
        if (null != savedAssesment && savedAssesment.getAid() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<AssesmentT> findAllAssesments() {
//        List<RegisterSt> studentDisp = reStRepository.findByCreatedBy(LmsConstants.ANONYMOUS);
        List<AssesmentT> assesmentDisp = assesmentRepository.findAll();
        return assesmentDisp;
    }
}
