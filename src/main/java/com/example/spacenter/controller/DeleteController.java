package com.example.spacenter.controller;

import com.example.spacenter.model.entity.BaseProcedure;
import com.example.spacenter.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

import static com.example.spacenter.service.CommonService.getAllProcedures;

@Controller
@ControllerAdvice
public class DeleteController {

    private final CommonService service;

    public DeleteController(CommonService service) {
        this.service = service;
    }

    @GetMapping("/delete/{id}")
    public String removeProcedure(@PathVariable Long id) {


        service.deleteProcedure(id);

        return "redirect:/home";
    }
}


