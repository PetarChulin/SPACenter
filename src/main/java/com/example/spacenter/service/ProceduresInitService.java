package com.example.spacenter.service;

import com.example.spacenter.model.entity.MedicalProcedure;
import com.example.spacenter.model.entity.SpaProcedure;
import com.example.spacenter.repositories.MedicalProceduresRepository;
import com.example.spacenter.repositories.SpaProceduresRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProceduresInitService {

    private MedicalProceduresRepository medicalProceduresRepository;
    private SpaProceduresRepository spaProceduresRepository;

    public ProceduresInitService(MedicalProceduresRepository medicalProceduresRepository,
                                 SpaProceduresRepository spaProceduresRepository) {
        this.medicalProceduresRepository = medicalProceduresRepository;
        this.spaProceduresRepository = spaProceduresRepository;
    }

    @PostConstruct
    private void initMedicalProcedures() {
        initSapropelProcedures();
        initLaserProcedures();
    }

    @PostConstruct
    private void initSpaProcedures() {
        initSpaRituals();
    }

    private void initSpaRituals() {
        var spaRitual = new SpaProcedure();
        if (checkForExistingSpa("SPA rituals")) return;

        spaRitual.setName("SPA rituals");
        spaRitual.setImageUrl("https://naturatermospa.com/wp-content/uploads/2019/09/spa-ritualai.jpg");
        spaRitual.setDescription("During SPA rituals, you will feel calm and experience unforgettable " +
                "moments in which beauty and harmony prevail.");

        this.spaProceduresRepository.save(spaRitual);

    }

    private void initSapropelProcedures() {
        var sapropelProcedure = new MedicalProcedure();
        if (checkForExistingMedical("Sapropel procedures")) return;


        sapropelProcedure.setName("Sapropel procedures");
        sapropelProcedure.setImageUrl("https://naturatermospa.com/wp-content/uploads/2019/09/gydomasis-purvas-sapropelis-1.jpg");
        sapropelProcedure.setDescription("In the Natura Termo SPA you can try unique procedures for which sapropel of 38–40 ºC temperature is used.");

        this.medicalProceduresRepository.save(sapropelProcedure);

    }

    private void initLaserProcedures() {
        var laserProcedure = new MedicalProcedure();
        if (checkForExistingMedical("Laser Procedures and Dermatology")) return;

        laserProcedure.setName("Laser Procedures and Dermatology");
        laserProcedure.setImageUrl("https://naturatermospa.com/wp-content/uploads/2020/05/dermo-432x324.jpg");
        laserProcedure.setDescription("Feel at ease choosing laser procedures performed by professional dermatologists.");

        this.medicalProceduresRepository.save(laserProcedure);

    }

    private boolean checkForExistingMedical(String name) {
        Optional<MedicalProcedure> entityName = this.medicalProceduresRepository.findByName(name);
        if (entityName.isPresent()) {
            System.out.println("This procedure exists");
            return true;
        }
        return false;
    }

    private boolean checkForExistingSpa(String name) {
        Optional<SpaProcedure> entityName = this.spaProceduresRepository.findByName(name);
        if (entityName.isPresent()) {
            System.out.println("This procedure exists");
            return true;
        }
        return false;
    }

}
