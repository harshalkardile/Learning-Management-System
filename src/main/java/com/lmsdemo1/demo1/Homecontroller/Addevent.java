package com.lmsdemo1.demo1.Homecontroller;


import com.lmsdemo1.demo1.model.AddEvent;
import com.lmsdemo1.demo1.service.AddBookService;

import com.lmsdemo1.demo1.service.AddEventService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
@Slf4j
@Controller

public class Addevent {

    @RequestMapping(value={
            "/addevent"
    })
    public String addEvent(Model model){

        model.addAttribute("addevent",new AddEvent());
        return "/LMS/classroom/addevent/index.html";
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

    private final AddEventService addeventService;


    @Autowired
    public  Addevent(AddEventService addeventService) {
        this.addeventService = addeventService;
    }

    @RequestMapping(value = "/saveEvent",method = POST)
    public String saveMessage(@Valid @ModelAttribute("addevent") AddEvent event, Errors errors){
        if(errors.hasErrors()){
            log.error("This Form Failed Deu To Error" + errors);
            return "/LMS/classroom/addevent/index.html";

        }



        addeventService.saveMessageDetails(event);
//        addeventService.setEventcount(addeventService.getEventcount()+1);
       // log.info("Number of times the addevent  form is submitted : "+addeventService.getEventcount());
        return "redirect:/addevent";
    }


    @RequestMapping("/displayaddeventMessages")
    public ModelAndView displayMessages(Model model) {
        List<AddEvent> eventMsgs = addeventService.findMsgsWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("/LMS/classroom/eventmessages.html");
        modelAndView.addObject("eventMsgs",eventMsgs);
        return modelAndView;
    }


    @RequestMapping(value = "/closeStatus",method = GET)
    public String closeMsg(@RequestParam int id) {
        addeventService.updateMsgStatus(id);
        return "redirect:/displayaddeventMessages";
    }




}
