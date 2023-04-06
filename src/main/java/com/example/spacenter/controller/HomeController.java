package com.example.spacenter.controller;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.service.MedicalSubProceduresService;
import com.example.spacenter.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private SearchService service;

    public HomeController(SearchService service) {
        this.service = service;
    }

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Locale locale, Model model, String keyword) {
        logger.info("Welcome to Healthy Spa Center homepage! Your locale is {}.", locale);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate );

        if (keyword != null) {
            List<Object> list = service.findByKeyword(keyword);
            model.addAttribute("list", list);
        } else {
            model.addAttribute("list", null);
        }
        return "index";
    }


//    @RequestMapping(path = {"/home"})
//    public String home(Model model, String keyword) {
//        if (keyword != null) {
//            List<Object> list = service.findByKeyword(keyword);
//            model.addAttribute("list", list);
//        } else {
//            model.addAttribute("list", null);
//        }
//        return "index";
//    }
}
