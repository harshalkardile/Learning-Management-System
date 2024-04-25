package com.lmsdemo1.demo1.Homecontroller;
import com.lmsdemo1.demo1.Repository.CoursecardRepo;
import com.lmsdemo1.demo1.Repository.RegistrationRepo;
import com.lmsdemo1.demo1.model.Coursecard;
import com.lmsdemo1.demo1.model.RegisterSt;
import com.lmsdemo1.demo1.model.cdvideo;
import com.lmsdemo1.demo1.service.CoursecardService;
import com.lmsdemo1.demo1.service.cdservice;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;


@Slf4j
@Controller
public class CoursecardController {






    @RequestMapping(value={
            "/coursedetail"
    })
    public String Coursepage2(){
//        List<cdvideo> list=cds.displayfile(id);
//        model.addAttribute("video",list);


        return "/LMS/Courses/coursedetail.html";
    }



    @Autowired
    RegistrationRepo registrationRep;



    @RequestMapping(value={"/coursespage"} )
    public String Coursepage0(HttpSession session, Authentication authentication){
//        String name=(String) session.getAttribute("userfirstname");
//        session.setAttribute("username",name);

        RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;
        session.setAttribute("userfirstname",registerSt.getRoles().getRoleName());

        return "/LMS/Courses/Coursehome.html";

    }




    @RequestMapping(value={"/coursecontain"})
    public ModelAndView Coursepage1( Model model){
        List<Coursecard> list=coursecardService.displaycard();

        ModelAndView  modelAndView = new ModelAndView("/LMS/Courses/coursenav.html");
        modelAndView.addObject("cardMsgs",list);
        model.addAttribute("card",new Coursecard());
      //  model.addAttribute("edit",new Coursecard());
        return modelAndView;
    }

    @Autowired
    private CoursecardService coursecardService;

    @Autowired
    private cdservice cds;


    @RequestMapping(value={"/coursesupload"} ,method ={RequestMethod.POST} )
    public String upload(@RequestParam ("updata")MultipartFile multipartFile,Coursecard coursecard) throws IOException {

                        System.out.println(multipartFile.getOriginalFilename());

                        if(!multipartFile.isEmpty()){
                            String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
                               coursecard.setCcimage(fileName);
                               String upload="src/main/resources/static/image";




                            String original=coursecard.getVideolink();
                            int endIndex = original.lastIndexOf("/");
                            String newln="https://www.youtube.com/embed"+original.substring(endIndex);

                            coursecard.setVideolink(newln);



                              Coursecard cc=coursecardService.uploadimage(coursecard);
                              Path uploadpath= Paths.get(upload);
                            if (!Files.exists(uploadpath)){
                                Files.createDirectories(uploadpath);
                            }
                            try(InputStream inputStream=multipartFile.getInputStream()){

                                Path filePath=uploadpath.resolve(fileName);
                                Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);

                            }


                            catch (IOException e){
            throw new IOException("Could not save file"+fileName,e);
                            }

                        }


                        else {
                        if (coursecard.getCcimage().isEmpty())
                        {
                            coursecard.setCcimage(null);
                            coursecardService.uploadimage(coursecard);
                        }


                        }

coursecardService.uploadimage(coursecard);

        return "redirect:/coursecontain";

    }

    @RequestMapping(value={"/editform/{id}"},method = {RequestMethod.GET})
    public String editcard(@PathVariable int id ,Model model){

System.out.println("/n/n/n/n/n/n"+id);
//        ModelAndView modelAndView=new ModelAndView("/LMS/Courses/coursenav.html");
       // Optional<Coursecard> list=coursecardService.editform(id);
        Coursecard coursecard=coursecardService.editform(id);
//Coursecard pro= list.get();
model.addAttribute("edited",coursecard);
//modelAndView.addObject("edited",coursecard);



        return "/LMS/Courses/editcard.html";
    }


    @RequestMapping(value = {"/editedupdated"} ,method = {RequestMethod.POST})
public String updateeditform(@RequestParam ("photo") MultipartFile multipartFile,@ModelAttribute("edited") Coursecard coursecard,HttpSession session) throws IOException {
        System.out.println(multipartFile.getOriginalFilename());

        if(!multipartFile.isEmpty()){
            String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
            coursecard.setCcimage(fileName);
            String upload="src/main/resources/static/image";


            String original=coursecard.getVideolink();
            int endIndex = original.lastIndexOf("/");
            String newln="https://www.youtube.com/embed"+original.substring(endIndex);
            coursecard.setVideolink(newln);

//            Coursecard cc=coursecardService.uploadimage(coursecard);
//            Coursecard existingcourse = coursecardService.updateform(id);
//            existingcourse.setCc_id(id);
//            existingcourse.setCctitle(coursecard.getCctitle());
//            existingcourse.setCcimage(coursecard.getCcimage());
//            existingcourse.setCcdescrip(coursecard.getCcdescrip());

            // save updated student object
            coursecardService.updateCourseform(coursecard);
            Path uploadpath= Paths.get(upload);
            if (!Files.exists(uploadpath)){
                Files.createDirectories(uploadpath);
            }
            try(InputStream inputStream=multipartFile.getInputStream()){
                Path filePath=uploadpath.resolve(fileName);
                Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException e){
                throw new IOException("Could not save file"+ fileName , e);
            }
            session.setAttribute("msg","The User has successfully  Added" );

            return "redirect:/coursecontain";
        }
        else {
            if (coursecard.getCcimage().isEmpty()) {
                coursecard.setCcimage(null);
                coursecardService.uploadimage(coursecard);
            }
        }
        return "redirect:/coursecontain";
    }

    @RequestMapping(value={"/deleteform/{id}"},method = {RequestMethod.GET})
    public String Deletecard(@PathVariable int id , HttpSession session){
        coursecardService.delete(id);

        session.setAttribute("msg","The User has successfully Existed" );
        return "redirect:/coursecontain";
    }

    @RequestMapping(value={"/uploaded"},method = {RequestMethod.POST})
   public String uploadvideo(@RequestParam("videoFile") MultipartFile file){

        cds.uploadVideo(file);

        return "redirect:/coursecontain";
   }


@RequestMapping(value={"/courseDetials/{id}"},method = {RequestMethod.GET})
   public String displayVideo(@PathVariable int id, Model model) throws IOException {
        Optional<Coursecard> list=cds.displayfile(id);
        if(list.isPresent()){
            Coursecard c1=list.get();

            model.addAttribute("card",c1);
    }









//        byte[] s=cdv.getData();
//    File tempFile = File.createTempFile("video", ".mp4");
//    FileUtils.writeByteArrayToFile(tempFile, s);
//    Resource videoResource = new FileSystemResource(tempFile);
////        ModelAndView modelAndView=new ModelAndView("/LMS/Courses/coursedetail.html");
////        modelAndView.addObject("video",s);
//        model.addAttribute("vd",videoResource);
        return  "/LMS/Courses/coursedetail.html";

//    String contentType = "video/mp4"; // Update with the appropriate content type for your video file

//    return ResponseEntity.ok()
//            .contentType(MediaType.parseMediaType(contentType))
//            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + cdv.getData())
//            .body(videoResource);

    }


    @Autowired
    CoursecardRepo coursecardRepo;


    @Autowired
    RegistrationRepo registrationRepo;


    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam int id
            ,HttpSession session,@RequestParam(required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("/LMS/Courses/coursestudents.html");
        Optional<Coursecard> courses = coursecardRepo.findById(id);
        modelAndView.addObject("courses",courses.get());
        modelAndView.addObject("person",new RegisterSt());
        session.setAttribute("courses",courses.get());
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;

    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(Model model, @ModelAttribute("person") RegisterSt registerSt,
                                           HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Coursecard courses = (Coursecard) session.getAttribute("courses");
        RegisterSt registerSt1 = registrationRepo.readByEmail(registerSt.getEmail());
        if(registerSt1==null || !(registerSt1.getId()>0)){
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCc_id()
                    +"&error=true");
            return modelAndView;
        }
        registerSt1.getCoursecard().add(courses);
        courses.getRegisterSts().add(registerSt1);
        registrationRepo.save(registerSt1);
        session.setAttribute("courses",courses);
        modelAndView.setViewName("redirect:/viewStudents?id="+courses.getCc_id());
        return modelAndView;
    }



    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(Model model, @RequestParam int personId,
                                                HttpSession session) {
        Coursecard courses = (Coursecard) session.getAttribute("courses");
        Optional<RegisterSt> person = registrationRepo.findById(personId);
        person.get().getCoursecard().remove(courses);
        courses.getRegisterSts().remove(person);
        registrationRepo.save(person.get());
        session.setAttribute("courses",courses);
        ModelAndView modelAndView = new
                ModelAndView("redirect:/viewStudents?id="+courses.getCc_id());
        return modelAndView;
    }




}



