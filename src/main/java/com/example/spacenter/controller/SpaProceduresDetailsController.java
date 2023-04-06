package com.example.spacenter.controller;

import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.model.entity.SpaProcedures.SpaServices;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpaProceduresDetailsController {

    private final SpaRitualsRepository spaRitualsRepository;
    private final SpaServicesRepository spaServicesRepository;

    public SpaProceduresDetailsController(SpaRitualsRepository spaRitualsRepository, SpaServicesRepository spaServicesRepository) {
        this.spaRitualsRepository = spaRitualsRepository;
        this.spaServicesRepository = spaServicesRepository;
    }

    @GetMapping("/spa-rituals/details/{id}")
    public ModelAndView spaRitualsDetails(@PathVariable Long id, ModelAndView model) {

        SpaRituals searched = this.spaRitualsRepository.findById(id).orElseThrow();
        model.setViewName("details");
        model.addObject("searched" , searched);

        return model;
    }

    @GetMapping("/spa-services/details/{id}")
    public ModelAndView spaServicesDetails(@PathVariable Long id, ModelAndView model) {

        SpaServices searched = this.spaServicesRepository.findById(id).orElseThrow();
        model.setViewName("details");
        model.addObject("searched" , searched);

        return model;
    }
}
