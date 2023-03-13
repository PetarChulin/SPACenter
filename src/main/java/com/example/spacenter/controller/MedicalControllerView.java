package com.example.spacenter.controller;

import com.example.spacenter.model.entity.MedicalProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.repositories.MedicalProceduresRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MedicalControllerView {

    private final MedicalProceduresRepository medicalProceduresRepository;

    private final SapropelRepository sapropelRepository;

    private final LoggedUser loggedUser;
    private final LaserRepository laserRepository;

    public MedicalControllerView(MedicalProceduresRepository medicalProceduresRepository, SapropelRepository sapropelRepository, LoggedUser loggedUser, LaserRepository laserRepository) {
        this.medicalProceduresRepository = medicalProceduresRepository;
        this.sapropelRepository = sapropelRepository;
        this.loggedUser = loggedUser;
        this.laserRepository = laserRepository;
    }

    @GetMapping("/")
    public String showOrders(Model model){

        Long userId = this.loggedUser.getId();

        List<SapropelProcedure> sapropelOrders = this.sapropelRepository.findByBuyers_Id(userId);

        int countOfSapropelOrders = sapropelOrders.size();
        model.addAttribute("countOfSapropelOrders" , countOfSapropelOrders);

        return "/index";
    }

    @GetMapping("/medical-procedures")
    public String medical(Model model) {

        List<MedicalProcedure> procedures = this.medicalProceduresRepository.findAll();
        Long userId = this.loggedUser.getId();

        List<SapropelProcedure> sapropelOrders = this.sapropelRepository.findByBuyers_Id(userId);
        int countOfSapropelOrders = sapropelOrders.size();
        model.addAttribute("countOfSapropelOrders" , countOfSapropelOrders);
        model.addAttribute("procedures" , procedures);

        return "medical-procedures";
    }



    @GetMapping("/SapropelProcedures/sapropel-procedures")
    public String sapropel(Model model) {

        List<SapropelProcedure> procedures = this.sapropelRepository.findAll();

        model.addAttribute("sapropelProcedures" , procedures);
//        List<SapropelProcedure> sapropelOrders = this.sapropelRepository.findByBuyers_Id(userId);
//        int countOfSapropelOrders = sapropelOrders.size();
//        model.addAttribute("countOfSapropelOrders" , countOfSapropelOrders);

        return "SapropelProcedures/sapropel-procedures";
    }

    @GetMapping("LaserProcedures/laser-procedures")
    public String laser(Model model) {

        List<LaserProcedure> procedures = this.laserRepository.findAll();

        model.addAttribute("laserProcedures" , procedures);

        return "LaserProcedures/laser-procedures";
    }

    @GetMapping("TherapyProcedures/therapy-procedures")
    public String therapy (Model model) {

//        List<MedicalProcedure> procedures = this.medicalProceduresRepository.findAll();
//
//        model.addAttribute("sapropel-procedures" , procedures);

        return "TherapyProcedures/therapy-procedures";
    }

    @GetMapping("ParaffinProcedures/paraffin-procedures")
    public String paraffin(Model model) {

//        List<MedicalProcedure> procedures = this.medicalProceduresRepository.findAll();
//
//        model.addAttribute("sapropel-procedures" , procedures);

        return "ParaffinProcedures/paraffin-procedures";
    }


}
