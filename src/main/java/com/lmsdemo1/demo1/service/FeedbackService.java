package com.lmsdemo1.demo1.service;

import com.lmsdemo1.demo1.constants.AddEventConstant;
import com.lmsdemo1.demo1.model.FeedbackSt;
import com.lmsdemo1.demo1.model.RegisterTe;
import com.lmsdemo1.demo1.Repository.FeedbackRepository;
import com.lmsdemo1.demo1.Repository.RegTeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private RegTeRepository regTeRepository;



    public FeedbackService(){

        System.out.println("Feedback service has been initialized for the Student");
    }

    //    private static Logger log= LoggerFactory.getLogger(RegStService.class);
    public boolean saveMessageDetails(FeedbackSt feedbackSt){
    boolean isSaved=false;
        feedbackSt.setStatus(AddEventConstant.COMPLETED);
        feedbackSt.setDate(String.valueOf(LocalDateTime.now()));
        Optional<RegisterTe> registerTe=regTeRepository.findById(feedbackSt.getRegisterTe().getIdT());

        feedbackSt.setRegisterTe(registerTe.get());
        FeedbackSt savedFeedback= feedbackRepository.save(feedbackSt);
        if (null != savedFeedback && savedFeedback.getFid() > 0) {
            isSaved = true;

        }
        return isSaved;
    }
}


