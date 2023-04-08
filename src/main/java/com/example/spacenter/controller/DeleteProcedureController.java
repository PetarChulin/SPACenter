package com.example.spacenter.controller;

import com.example.spacenter.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@ControllerAdvice
public class DeleteProcedureController {

    private final CommonService service;

    public DeleteProcedureController(CommonService service) {
        this.service = service;
    }

    @GetMapping("/delete/procedureFromDB/{id}")
    public String removeProcedure(@PathVariable Long id) {


        service.deleteProcedure(id);

        return "redirect:/home";
    }
}


