package com.example.spacenter.controller;

import com.example.spacenter.model.dto.SpaProceduresDTO;
import com.example.spacenter.repositories.SpaProceduresRepository;
import com.example.spacenter.service.SpaProcedureService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SpaProcedureControllerAdd {

    private SpaProceduresRepository spaProceduresRepository;

    private SpaProcedureService spaProcedureService;

    public SpaProcedureControllerAdd(SpaProceduresRepository spaProceduresRepository, SpaProcedureService spaProcedureService) {
        this.spaProceduresRepository = spaProceduresRepository;
        this.spaProcedureService = spaProcedureService;
    }

    @ModelAttribute("spaProceduresDTO")
    public SpaProceduresDTO initSpaProceduresDTO(){return new SpaProceduresDTO();}


    @GetMapping("/spa/add")
    public String addSpa() {

        return "add-spa-procedures";

    }

    @PostMapping("/spa/add")
    public String spa(@Valid SpaProceduresDTO spaProceduresDTO,
                           BindingResult result,
                           RedirectAttributes attributes){

        if (result.hasErrors() || !this.spaProcedureService.add(spaProceduresDTO)) {
            attributes.addFlashAttribute("spaProceduresDTO", spaProceduresDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.spaProceduresDTO",
                    result);
            return "redirect:/spa/add";
        }

        return "redirect:/spa-procedures";
    }
}
