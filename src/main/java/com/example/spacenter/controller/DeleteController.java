package com.example.spacenter.controller;

import com.example.spacenter.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DeleteController {

    private final CommonService service;

    public DeleteController(CommonService service) {
        this.service = service;
    }

    @GetMapping("/delete/{id}")
    public String removeProcedure(@PathVariable Long id, RedirectAttributes attributes) {

        service.deleteProcedure(id);
        attributes.addFlashAttribute("deleted" , true);

        return "redirect:/home";
    }
}


