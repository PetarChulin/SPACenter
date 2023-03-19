package com.example.spacenter.controller;

import com.example.spacenter.model.entity.SpaProcedure;
import com.example.spacenter.repositories.SpaProceduresRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SpaProceduresControllerView {

    private SpaProceduresRepository spaProceduresRepository;

    public SpaProceduresControllerView(SpaProceduresRepository spaProceduresRepository) {
        this.spaProceduresRepository = spaProceduresRepository;
    }

    @GetMapping("/spa-procedures")
    public String spa(Model model) {

        List<SpaProcedure> spaProcedures = this.spaProceduresRepository.findAll();
        model.addAttribute("procedures" , spaProcedures);

        return "spa-procedures";
    }
}
