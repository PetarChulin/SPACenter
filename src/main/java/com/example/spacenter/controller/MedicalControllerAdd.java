package com.example.spacenter.controller;

import com.example.spacenter.model.dto.MedicalProcedureDTO.LaserProceduresDTO;
import com.example.spacenter.model.dto.MedicalProcedureDTO.SapropelProceduresDTO;
import com.example.spacenter.model.dto.MedicalProceduresDTO;
import com.example.spacenter.repositories.MedicalProceduresRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.service.AuthService;
import com.example.spacenter.service.MedicalProcedureService;
import com.example.spacenter.service.MedicalSubProceduresService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MedicalControllerAdd {

    private MedicalProceduresRepository medicalProceduresRepository;
    private MedicalProcedureService medicalProcedureService;

    private AuthService authService;
    private SapropelRepository sapropelRepository;

    private MedicalSubProceduresService medicalSubProceduresService;

    public MedicalControllerAdd(MedicalProceduresRepository medicalProceduresRepository, MedicalProcedureService medicalProcedureService, AuthService authService, SapropelRepository sapropelRepository, MedicalSubProceduresService medicalSubProceduresService) {
        this.medicalProceduresRepository = medicalProceduresRepository;
        this.medicalProcedureService = medicalProcedureService;
        this.authService = authService;
        this.sapropelRepository = sapropelRepository;
        this.medicalSubProceduresService = medicalSubProceduresService;
    }

    @ModelAttribute("medicalProceduresDTO")
    public MedicalProceduresDTO initMedicalProceduresDTO(){return new MedicalProceduresDTO();}

    @ModelAttribute("sapropelProceduresDTO")
    public SapropelProceduresDTO initSapropelProceduresDTO(){return new SapropelProceduresDTO();}

    @ModelAttribute("laserProceduresDTO")
    public LaserProceduresDTO initLaserProceduresDTO(){return new LaserProceduresDTO();}

    @GetMapping("/medical/add")
    public String add() {

//        if (!this.authService.isLoggedIn()) {
//            return "redirect:/medical-procedures";
//        }

        return "add-medical-procedures";

    }

    @GetMapping("/medical/add/sapropel")
    public String sapropel() {

        return "add-sapropel-procedures";

    }

    @GetMapping("/medical/add/laser")
    public String laser() {



        return "add-laser-procedures";

    }

    @PostMapping("/medical/add")
    public String medicals(@Valid MedicalProceduresDTO medicalProceduresDTO,
                           BindingResult result,
                           RedirectAttributes attributes){

        if (result.hasErrors() || !this.medicalProcedureService.add(medicalProceduresDTO)) {
            attributes.addFlashAttribute("medicalProceduresDTO", medicalProceduresDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.medicalProceduresDTO",
                    result);
            return "redirect:/medical/add";
        }

        return "redirect:/medical-procedures";
    }

    @PostMapping("/medical/add/sapropel")
    public String addSapropel(@Valid SapropelProceduresDTO sapropelProceduresDTO,
                           BindingResult result,
                           RedirectAttributes attributes){

        if (result.hasErrors() || !this.medicalSubProceduresService.addSapropelProcedure(sapropelProceduresDTO)) {
            attributes.addFlashAttribute("sapropelProceduresDTO", sapropelProceduresDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.sapropelProceduresDTO",
                    result);
            return "redirect:/medical/add/sapropel";
        }

        return "redirect:/SapropelProcedures/sapropel-procedures";
    }

    @PostMapping("/medical/add/laser")
    public String addLaser(@Valid LaserProceduresDTO laserProceduresDTO,
                              BindingResult result,
                              RedirectAttributes attributes){

        if (result.hasErrors() || !this.medicalSubProceduresService.addLaserProcedure(laserProceduresDTO)) {
            attributes.addFlashAttribute("laserProceduresDTO", laserProceduresDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.laserProceduresDTO",
                    result);
            return "redirect:/medical/add/laser";
        }

        return "redirect:/LaserProcedures/laser-procedures";
    }
}
