package com.lmsdemo1.demo1.Homecontroller;


import com.lmsdemo1.demo1.Repository.AddBookRepo;
import com.lmsdemo1.demo1.Repository.Issuedbookrepo;
import com.lmsdemo1.demo1.Repository.RegistrationRepo;
import com.lmsdemo1.demo1.model.AddBookModel;
import com.lmsdemo1.demo1.model.AddEvent;
import com.lmsdemo1.demo1.model.Issued;
import com.lmsdemo1.demo1.model.RegisterSt;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.SessionScope;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class Issuedbook {

    @Autowired
   private AddBookRepo addBookRepo;

    @Autowired
    private RegistrationRepo registrationRep;

    @Autowired
    private Issuedbookrepo issuedbookrepo;

    @RequestMapping(value={
            "/checkout/{id}"
    },method = {RequestMethod.GET})
    public String checkout(@PathVariable int id , Model model, Authentication authentication, HttpSession session){
        RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;


        Optional<AddBookModel> list=addBookRepo.findById(id);

        Long list2=issuedbookrepo.countByAddBookModelAddbookid(id);
//        Optional<AddBookModel> list = addBookService.displayreadbook(id);

        AddBookModel addBookModel;
        Issued issued= new Issued();
        if (list.isPresent() && list2==1 ) {
            addBookModel=list.get();
//            model.addAttribute("updatebook", addBookModel);
            issued.setBookname(addBookModel.getTitle());
            issued.setFirstname(registerSt.getFname());
            issued.setRegisterSt(registerSt);
            issued.setAddBookModel(addBookModel);
//            issued.setAddressi(registerSt.getAddress().getAddress1());
            issued.setStartdate(String.valueOf(LocalDate.now()));
            issued.setEnddate(String.valueOf(LocalDate.now().plusDays(7)));
            issued.setFine(String.valueOf(0));
            addBookRepo.save(addBookModel);
            issuedbookrepo.save(issued);
//             model.addAttribute("is",issued);
            System.out.println(issued);
            session.setAttribute("booksmsg","Request sended successfully");
            return "redirect:/libraryembed";
        }
        else
        {
            session.setAttribute("booksmsg","Request is Already Sended");
            return "redirect:/libraryembed";
        }
//        RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;






//        if(Integer.valueOf(issued.getEnddate().substring(8))>=LocalDate.now().getDayOfMonth())
//        {
//            issued.setFine(String.valueOf(Integer.valueOf(issued.getFine())+10));
//        }


//        model.addAttribute("user",registerSt);


    }

    @RequestMapping("/removebook")
    public String profileindexremove(HttpSession session, SessionScope sessionScope){
        session.removeAttribute("booksmsg");
        return "redirect:/libraryembed";
    }


    @RequestMapping(value = {"/issuedbookadmin"})
  public String listissuedbookadmin(Model model){

        List<Issued> is=issuedbookrepo.findAll();

        model.addAttribute("issued",is);


//        model.addAttribute("addevent",new AddEvent());
        return "/LMS/Library/checkout.html";
  }
    @RequestMapping(value = {"/issuedbook"})
    public String listissuedbook(Model model,Authentication authentication){

        RegisterSt registerSt= registrationRep.readByEmail(authentication.getName()) ;

        System.out.println(authentication.getName());


        List<Issued> is=issuedbookrepo.findAllByFirstname(registerSt.getFname());



        model.addAttribute("news",new Issued());

        model.addAttribute("issued",is);



//        model.addAttribute("addevent",new AddEvent());
        return "/LMS/Library/checkout.html";
    }


    @PostMapping(value = "/assignbook")
    public String assignissuedbook(@ModelAttribute("news") Issued next,Model model, Authentication authentication ,HttpSession session)
    {


        Issued list =issuedbookrepo.findByAddBookModelAddbookid(next.getAddBookModel().getAddbookid());
        AddBookModel addBookModel=addBookRepo.findByAddbookid(Integer.valueOf(next.getAddBookModel().getAddbookid()));

        System.out.println(list);
        System.out.println(next.getStartdate());
        System.out.println(list.getIbid());
        String available=list.getAddBookModel().getAvailable();
        System.out.println(available);
//      log.error(String.valueOf(issued));
      if(available=="YES")
        {
            session.setAttribute("msg","Book Is Already Assigned To Another User \t"+list.getFirstname());

            return "redirect:/issuedbook";

            }
        else{
          list.setStartdate(next.getStartdate());
          list.setEnddate(next.getEnddate());
          list.setBlink(addBookModel.getBook());
//              list.setBlink(next.getAddBookModel().getBook());
          addBookModel.setAvailable("YES");
          session.setAttribute("msg","Book Assigned Successfully to \t"+list.getFirstname());
          addBookRepo.save(addBookModel);
          issuedbookrepo.save(list);
          return "redirect:/issuedbook";
      }

//            model.addAttribute("news", issued);
//        }

//        Issued next=new Issued();
////        model.getAttribute("news",next);
    }


    @RequestMapping("/removeibook")
    public String profileindexremovemsg(HttpSession session, SessionScope sessionScope){
        session.removeAttribute("msg");

        return "redirect:/issuedbook";
    }


}
