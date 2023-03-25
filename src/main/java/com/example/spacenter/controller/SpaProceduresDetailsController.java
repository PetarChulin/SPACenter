package com.example.spacenter.controller;

import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.SpaProcedure;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpaProceduresDetailsController {

    private SpaRitualsRepository spaRitualsRepository;

    public SpaProceduresDetailsController(SpaRitualsRepository spaRitualsRepository) {
        this.spaRitualsRepository = spaRitualsRepository;
    }

    @GetMapping("/spa-rituals/details/{id}")
    public ModelAndView spaRitualsDetails(@PathVariable Long id, ModelAndView model) {

        SpaRituals searched = this.spaRitualsRepository.findById(id).orElseThrow();
        model.setViewName("/SPARituals/spa-rituals-details");
        model.addObject("searched" , searched);

        return model;
    }
}
