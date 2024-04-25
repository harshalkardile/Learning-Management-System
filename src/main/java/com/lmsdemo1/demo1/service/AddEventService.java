package com.lmsdemo1.demo1.service;




import com.lmsdemo1.demo1.Repository.AddEventRepo;
import com.lmsdemo1.demo1.constants.AddEventConstant;
import com.lmsdemo1.demo1.model.AddEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@SessionScope
public class AddEventService {
//    private int eventcount=0;


    @Autowired
    private AddEventRepo addEventRepo;

//    public AddEventService() {
//        System.out.println("bean event initialized");
//
//
//    }

//    public int getEventcount() {
//        return eventcount;
//    }
//
//    public void setEventcount(int eventcount) {
//        this.eventcount = eventcount;
//    }

    public boolean saveMessageDetails(AddEvent addevent){
        boolean isSaved = false;
        addevent.setStatus(AddEventConstant.OPEN);
//        addevent.setCreatedBy(AddEventConstant.ANONYMOUS);
//        addevent.setCreatedAt(LocalDateTime.now());
        AddEvent savedEvent = addEventRepo.save(addevent);
        if(null != savedEvent && savedEvent.getAddeventid()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public  List<AddEvent> findMsgsWithOpenStatus(){
        List<AddEvent> eventMsgs = addEventRepo.findByStatus(AddEventConstant.OPEN);
        return eventMsgs;
    }

    public boolean updateMsgStatus(int contactId){
       // boolean isUpdated = false;
//        int result = addEventRepo.updateMsgStatus(contactId,AddEventConstant.CLOSE, updatedBy);
//        if(result>0) {
//            isUpdated = true;
//        }
        boolean isUpdated = false;
        Optional<AddEvent> contact = addEventRepo.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(AddEventConstant.CLOSE);
//            contact1.setUpdatedBy(updatedBy);
//            contact1.setUpdatedAt(LocalDateTime.now());
        });
        AddEvent updatedContact = addEventRepo.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy()!=null) {
            isUpdated = true;
        }


        return isUpdated;
    }


}
