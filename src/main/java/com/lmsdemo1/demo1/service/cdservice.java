package com.lmsdemo1.demo1.service;


import com.lmsdemo1.demo1.Repository.CoursecardRepo;
import com.lmsdemo1.demo1.Repository.cdrepo;
import com.lmsdemo1.demo1.model.Coursecard;
import com.lmsdemo1.demo1.model.cdvideo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@SessionScope
public class cdservice {

    @Autowired
     private cdrepo cdr;

    @Autowired
    private CoursecardRepo coursecardRepo;

    public  void uploadVideo(MultipartFile file) {
        try {
            byte[] videoBytes = file.getBytes();

            cdvideo video = new cdvideo();
            video.setCcvideo(file.getOriginalFilename());
            video.setData(videoBytes);

            cdr.save(video);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any errors that occur during the file upload
        }
    }


public Optional<Coursecard> displayfile(int id)
{


    return coursecardRepo.findById(id);

}



}


