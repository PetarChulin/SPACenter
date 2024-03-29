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
    public void initMedicalProcedures() {
        initSapropelProcedures();
        initLaserProcedures();
    }

    @PostConstruct
    public void initSpaProcedures() {

        initSpaRituals();
        initSpaCenterServices();
    }

    private void initSpaRituals() {
        var spaRitual = new SpaProcedure();
        if (checkForExistingSpa("SPA rituals")) return;

        spaRitual.setId((long) Math.floor(Math.random() * 120));
        spaRitual.setName("SPA rituals");
        spaRitual.setImageUrl("/images/spa_center/spa-ritualai.jpg");
        spaRitual.setDescription("During SPA rituals, you will feel calm and experience unforgettable " +
                "moments in which beauty and harmony prevail.");

        this.spaProceduresRepository.save(spaRitual);

    }

    private void initSpaCenterServices() {
        var spaServices = new SpaProcedure();
        if (checkForExistingSpa("SPA center")) return;

        spaServices.setId((long) Math.floor(Math.random() * 110));
        spaServices.setName("SPA center");
        spaServices.setImageUrl("/images/spa_center/hammam.jpg");
        spaServices.setDescription("The SPA centre invites you to the kingdom of beauty." +
                "Try out the exclusive SPA services.");

        this.spaProceduresRepository.save(spaServices);

    }

    private void initSapropelProcedures() {

        var sapropelProcedure = new MedicalProcedure();
        if (checkForExistingMedical("Sapropel procedures")) return;

        sapropelProcedure.setId((long) Math.floor(Math.random() * 150));
        sapropelProcedure.setName("Sapropel procedures");
        sapropelProcedure.setImageUrl("/images/sapropel/image-2.jpeg");
        sapropelProcedure.setDescription("In the Natura Termo SPA you can try unique procedures for which sapropel of 38–40 ºC temperature is used.");

        this.medicalProceduresRepository.save(sapropelProcedure);

    }

    private void initLaserProcedures() {
        var laserProcedure = new MedicalProcedure();
        if (checkForExistingMedical("Laser Procedures and Dermatology")) return;

        laserProcedure.setId((long) Math.floor(Math.random() * 140));
        laserProcedure.setName("Laser Procedures and Dermatology");
        laserProcedure.setImageUrl("/images/laser/dermo.jpg");
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
