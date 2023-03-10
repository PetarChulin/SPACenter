package com.example.spacenter.controller;

import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DetailsController {

    private final SapropelRepository sapropelRepository;

    public DetailsController(SapropelRepository sapropelRepository) {
        this.sapropelRepository = sapropelRepository;

    }


    @GetMapping("/sapropel/details/{id}")
    public ModelAndView sapropelDetails(@PathVariable Long id, ModelAndView model) {

        SapropelProcedure searched = this.sapropelRepository.findById(id).orElseThrow();
        model.setViewName("SapropelProcedures/sapropel-details");
        model.addObject("searched" , searched);

        return model;
    }
}
