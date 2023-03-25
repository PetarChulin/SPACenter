package com.example.spacenter.controller;

import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MedicalProceduresDetailsController {

    private final SapropelRepository sapropelRepository;

    private final LaserRepository laserRepository;

    public MedicalProceduresDetailsController(SapropelRepository sapropelRepository, LaserRepository laserRepository) {
        this.sapropelRepository = sapropelRepository;

        this.laserRepository = laserRepository;
    }


    @GetMapping("/sapropel/details/{id}")
    public ModelAndView sapropelDetails(@PathVariable Long id, ModelAndView model) {

        SapropelProcedure searched = this.sapropelRepository.findById(id).orElseThrow();
        model.setViewName("SapropelProcedures/sapropel-details");
        model.addObject("searched" , searched);

        return model;
    }

    @GetMapping("/laser/details/{id}")
    public ModelAndView laserDetails(@PathVariable Long id, ModelAndView model) {

        LaserProcedure searched = this.laserRepository.findById(id).orElseThrow();
        model.setViewName("LaserProcedures/laser-details");
        model.addObject("searched" , searched);

        return model;
    }
}
