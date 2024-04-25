package com.lmsdemo1.demo1.Homecontroller;



import com.lmsdemo1.demo1.model.AddBookModel;
import com.lmsdemo1.demo1.model.Coursecard;
import com.lmsdemo1.demo1.model.RegisterSt;
import com.lmsdemo1.demo1.service.AddBookService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
@Slf4j
@Controller
public class Addbook {

    @RequestMapping(value={
            "/addbook"
    })
    public String addbook( Model model){
//        List<AddBookModel> list=addBookService.displaybook();
        model.addAttribute("addBookModel",new AddBookModel());
        return "/LMS/Library/hexashop-1.0.0/addbook.html";
    }



    //    private static Logger log= LoggerFactory.getLogger(LoginModel.class);
//    @RequestMapping(value = "/savebook",method = POST)
//    public ModelAndView saveMessage(@RequestParam String title, @RequestParam String author,
//                                    @RequestParam String publisher, @RequestParam String genre, @RequestParam String image,@RequestParam String book,@RequestParam String year ) {
//        log.info("Title : " + title );
//        log.info("Auther: " + author );
//        log.info("Publisher : " + publisher);
//        log.info("Generic : " + genre);
//        log.info("Image : " + image);
//        log.info("Book : " + book);
//        log.info("Year : " + year);
//        return new ModelAndView("redirect:/addbook");
//    }

    private final AddBookService addBookService;

    @Autowired
    public Addbook(AddBookService addBookService) {
        this.addBookService = addBookService;
    }




    @RequestMapping(value = "/savebook",method = POST)
    public String saveMessage(@RequestParam("updata")MultipartFile multipartFile, AddBookModel addBookModel) throws IOException {
//            if(errors.hasErrors()){
//            log.error("This Form Failed Deu To Error" + errors);
//            return "/LMS/Library/hexashop-1.0.0/addbook.html";
//            }

        if(!multipartFile.isEmpty()){
            String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
//            String fileName1= StringUtils.cleanPath(bookfile.getOriginalFilename());

            addBookModel.setImage(fileName);
//            addBookModel.setBook(fileName1);
            String url=addBookModel.getBook();
            int endIndex = url.lastIndexOf("/");
            String cutUrl = url.substring(0, endIndex)+"/preview";
            addBookModel.setBook(cutUrl);






            String upload="src/main/resources/static/image";


            AddBookModel cc;
            cc=addBookService.uploadimg(addBookModel);


            Path uploadpath= Paths.get(upload);



            if (!Files.exists(uploadpath)){
                Files.createDirectories(uploadpath);

            }
//            InputStream bkstream;

            try(InputStream inputStream=multipartFile.getInputStream()){

//                bkstream=bookfile.getInputStream();



                Path filePath=uploadpath.resolve(fileName);
//                Path filePath1=uploadpath.resolve(fileName1);

                Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
//                Files.copy(bkstream,filePath1, StandardCopyOption.REPLACE_EXISTING);



            }


            catch (IOException e){
                throw new IOException("Could not save file"+fileName+"could not save book"+e);
            }

        }

        else {
            if (addBookModel.getImage().isEmpty() && addBookModel.getBook().isEmpty())
            {
                addBookModel.setImage(null);
                addBookModel.setBook(null);
                addBookService.uploadimg(addBookModel);
            }
}

        addBookService.uploadimg(addBookModel);

//        addBookService.saveMessageDetails(addBookModel);
//        addBookService.setCounter(addBookService.getCounter()+1);
//        log.info("Number of times the Contact form is submitted : "+addBookService.getCounter());
//        log.info("Number of times the Contact form is submitted : "+addBookModel);


        return "redirect:/libraryembed";
    }

    @RequestMapping(value={"/deletebook/{id}"},method = {RequestMethod.GET})
    public String Deletebook(@PathVariable int id , HttpSession session){
        addBookService.bookdelete(id);

        session.setAttribute("msg","The book is  has successfully Deleted" );
        return "redirect:/libraryembed";
    }




    @RequestMapping(value={"/readbook/{id}"},method = {RequestMethod.GET})
    public String readbook(@PathVariable int id , Model model) {
        Optional<AddBookModel> list = addBookService.displayreadbook(id);
        if (list.isPresent()) {
            AddBookModel addBookModel=list.get();
            model.addAttribute("addBookModel", addBookModel);
        }
            return "/LMS/Library/read.html";
    }


    @RequestMapping(value={"/getchangebook/{id}"},method = {RequestMethod.GET})
    public String changebook(@PathVariable int id , Model model) {
        Optional<AddBookModel> list = addBookService.displayreadbook(id);
        if (list.isPresent()) {
            AddBookModel addBookModel=list.get();
            model.addAttribute("updatebook", addBookModel);
        }
        return "/LMS/Library/updateaddbook.html";
    }


    @RequestMapping(value = {"/changebook"} ,method = {RequestMethod.POST})
    public String updatebookform(@RequestParam ("update") MultipartFile multipartFile,@ModelAttribute("updatebook") AddBookModel addBookModel, HttpSession session) throws IOException {

        if(!multipartFile.isEmpty()){
            String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
//            String fileName1= StringUtils.cleanPath(bookfile.getOriginalFilename());

            addBookModel.setImage(fileName);
//            addBookModel.setBook(fileName1);
            String url=addBookModel.getBook();
            int endIndex = url.lastIndexOf("/");
            String cutUrl = url.substring(0, endIndex)+"/preview";
            addBookModel.setBook(cutUrl);

            String upload="src/main/resources/static/image";
            AddBookModel cc;
            cc=addBookService.uploadimg(addBookModel);


            Path uploadpath= Paths.get(upload);



            if (!Files.exists(uploadpath)){
                Files.createDirectories(uploadpath);

            }

            try(InputStream inputStream=multipartFile.getInputStream()){
                Path filePath=uploadpath.resolve(fileName);

                Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException e){
                throw new IOException("Could not save file"+fileName+"could not save book"+e);
            }

        }

        else {
            if (addBookModel.getImage().isEmpty() && addBookModel.getBook().isEmpty())
            {
                addBookModel.setImage(null);
                addBookModel.setBook(null);
                addBookService.uploadimg(addBookModel);
            }
        }

        addBookService.uploadimg(addBookModel);

        return "redirect:/libraryembed";

//        addBookService.updatebook(addBookModel);
//
//        session.setAttribute("msg","The User has successfully  Added" );
//
//        return "redirect:/libraryembed";


    }




}
