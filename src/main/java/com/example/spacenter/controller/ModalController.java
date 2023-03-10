package com.example.spacenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModalController {

    @GetMapping("/already-bought")
    public String index(Model model) {


        return "/modal/already-in-cart";
    }
}
