package com.lmsdemo1.demo1.Homecontroller;



import com.lmsdemo1.demo1.Repository.HolidaysRepo;
import com.lmsdemo1.demo1.model.Holiday;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Slf4j
@Controller
public class HolidayController {

//    @GetMapping("/holidays/{display}")
//    public String displayHolidays(@RequestParam(required = false) boolean festival,
//                                  @RequestParam(required = false) boolean federal,Model model) {
//        model.addAttribute ( "festival" , festival);
//        model.addAttribute ( "federal" , federal);

    @Autowired
    private HolidaysRepo holidaysRepo;

        @GetMapping("/holidays/{display}")
        public String displayHolidays(@PathVariable String display, Model model) {
            if(null!= display && display.equals("all")){
                    model. addAttribute ("festival" , true) ;
                    model. addAttribute( "federal" , true) ;}
        else if(null!= display && display.equals("federal")){
                 model. addAttribute( "federal" , true) ;}
        else if(null!= display && display.equals("festival")){
                model. addAttribute( "festival" , true) ;}

//
//            List<Holiday> holidays = holidaysRepo.findAllHolidays();
//        Holiday.Type[] types = Holiday.Type.values();
//        for (Holiday.Type type : types) {
//            model.addAttribute(type.toString(),
//                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
//        }


            Iterable<Holiday> holidays = holidaysRepo.findAll();
            List<Holiday> holidayList = StreamSupport
                    .stream(holidays.spliterator(), false)
                    .collect(Collectors.toList());

            Holiday.Type[] types = Holiday.Type.values();
            for (Holiday.Type type : types) {
                model.addAttribute(type.toString(),
                        (holidayList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
            }



        return "/LMS/classroom/holidays.html";
    }
}