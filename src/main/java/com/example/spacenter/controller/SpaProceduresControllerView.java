package com.example.spacenter.controller;

import com.example.spacenter.model.entity.SpaProcedure;
import com.example.spacenter.repositories.SpaProceduresRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.service.SpaSubProceduresService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SpaProceduresControllerView {

    private SpaProceduresRepository spaProceduresRepository;

    private SpaRitualsRepository spaRitualsRepository;

    private SpaSubProceduresService spaSubProceduresService;

    public SpaProceduresControllerView(SpaProceduresRepository spaProceduresRepository, SpaRitualsRepository spaRitualsRepository, SpaSubProceduresService spaSubProceduresService) {
        this.spaProceduresRepository = spaProceduresRepository;
        this.spaRitualsRepository = spaRitualsRepository;
        this.spaSubProceduresService = spaSubProceduresService;
    }

    @GetMapping("/spa-procedures")
    public String spaProcedures(Model model) {

        List<SpaProcedure> spaProcedures = this.spaProceduresRepository.findAll();
        model.addAttribute("procedures" , spaProcedures);

        return "spa-procedures";
    }

    @GetMapping("/SPARituals/spa-rituals")
    public String spaRituals(Model model, @PageableDefault(sort = "name", size = 4) Pageable pageable) {

        var procedures = this.spaSubProceduresService.getAllSpaRituals(pageable);

        model.addAttribute("spaRituals" , procedures);
        model.addAttribute("totalPages" , procedures.getTotalPages());


        return "SPARituals/spa-rituals";
    }
}
