package com.lmsdemo1.demo1.service;


import com.lmsdemo1.demo1.Repository.CoursecardRepo;
import com.lmsdemo1.demo1.model.Coursecard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@SessionScope
@Service
public class CoursecardService {


    @Autowired
    private CoursecardRepo coursecardRepo;

//    @Autowired
//    private Coursecard coursecard;



public Coursecard  uploadimage(Coursecard coursecard){


    return coursecardRepo.save(coursecard);



//                        boolean isUpdated = false;
//
//                        coursecard=coursecardRepo.save(image);
//
//                        if (coursecard != null) {
//                            try {
//
//                                File saveFile = new ClassPathResource("static/img").getFile();
//
//                                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + imgpath.getOriginalFilename());
//                                //System.out.println(path);
//                                Files.copy(imgpath.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//                                isUpdated=true;
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                      //  session.setAttribute("msg", "Image Upload Sucessfully");
//
//
//                    return isUpdated;


}

public List<Coursecard> displaycard(){
   // List<Coursecard> cardMsgs=coursecardRepo.findAll();
    return coursecardRepo.findAll(Sort.by("cctitle").descending());
}

public Coursecard editform(Integer id){
    Optional<Coursecard> optional=coursecardRepo.findById(id);
Coursecard coursecard=null;
if(optional.isPresent()){
    coursecard=optional.get();
}
else {
    throw new RuntimeException("**********Detial are not found::"+id);
}



    return coursecard;

}


public Coursecard updateform(int id)
{
    return coursecardRepo.findById(id).get();
}


    public Coursecard updateCourseform(Coursecard student) {
        return coursecardRepo.save(student);
    }




public void delete(Integer id)
{
                    Long count=coursecardRepo.countByCcId();
                    if(count==null || count==0){

             System.out.println(count);

                    }
                    coursecardRepo.deleteById(id);
}







}
