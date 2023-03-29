package com.example.spacenter.controller;

import com.example.spacenter.model.entity.MedicalProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.repositories.MedicalProceduresRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.service.MedicalSubProceduresService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static com.example.spacenter.service.CommonService.getUserId;


@Controller
public class MedicalControllerView {

    private final MedicalProceduresRepository medicalProceduresRepository;

    private final MedicalSubProceduresService medicalSubProceduresService;

    private final SapropelRepository sapropelRepository;

    private final LaserRepository laserRepository;

    public MedicalControllerView(MedicalProceduresRepository medicalProceduresRepository, MedicalSubProceduresService medicalSubProceduresService,
                                 SapropelRepository sapropelRepository,  LaserRepository laserRepository) {
        this.medicalProceduresRepository = medicalProceduresRepository;
        this.medicalSubProceduresService = medicalSubProceduresService;
        this.sapropelRepository = sapropelRepository;
        this.laserRepository = laserRepository;
    }

    @GetMapping("/")
    public String showOrders(Model model){

        Long userId = getUserId();

        List<SapropelProcedure> sapropelOrders = this.sapropelRepository.findByBuyers_Id(userId);

        int countOfSapropelOrders = sapropelOrders.size();

        model.addAttribute("countOfSapropelOrders" , countOfSapropelOrders);

        return "/index";
    }

    @GetMapping("/medical-procedures")
    public String medical(Model model) {

        List<MedicalProcedure> procedures = this.medicalProceduresRepository.findAll();


        model.addAttribute("mprocedures" , procedures);

        return "medical-procedures";
    }

//    @GetMapping(path = "/medical-procedures", consumes="application/json")
//    public ResponseEntity<List<MedicalProcedure>> getAllMedical() {
//        var procedures = this.medicalProceduresRepository.findAll();
//
//        return ResponseEntity.ok(procedures);
//    }



    @GetMapping("/SapropelProcedures/sapropel-procedures")
    public String sapropel(Model model, @PageableDefault(sort = "name", size = 4) Pageable pageable) {


        var procedures = medicalSubProceduresService.getAllSapropel(pageable);

        model.addAttribute("sapropelProcedures" , procedures);
        model.addAttribute("totalPages" , procedures.getTotalPages());

        return "SapropelProcedures/sapropel-procedures";
    }

    @GetMapping("LaserProcedures/laser-procedures")
    public String laser(Model model, @PageableDefault(sort = "name", size = 4) Pageable pageable) {


        var procedures = medicalSubProceduresService.getAllLaser(pageable);


        model.addAttribute("laserProcedures" , procedures);
        model.addAttribute("totalPages" , procedures.getTotalPages());


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
