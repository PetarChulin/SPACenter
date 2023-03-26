package com.example.spacenter.service;

import com.example.spacenter.model.entity.MedicalProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubProceduresInitService {

    private SapropelRepository sapropelRepository;
    private LaserRepository laserRepository;
    private SpaRitualsRepository spaRitualsRepository;

    public SubProceduresInitService(SapropelRepository sapropelRepository, LaserRepository laserRepository, SpaRitualsRepository spaRitualsRepository) {
        this.sapropelRepository = sapropelRepository;
        this.laserRepository = laserRepository;
        this.spaRitualsRepository = spaRitualsRepository;
    }

    @PostConstruct
    private void initSapropelProcedures() {
        initSapropelProcedure1();
        initSapropelProcedure2();
        initSapropelProcedure3();
        initSapropelProcedure4();
    }

    private void initSapropelProcedure4() {

        var sapropel4 = new SapropelProcedure();
        if (checkForExistingSapropel("Full body wrap with sapropel")) return;


        sapropel4.setName("Full body wrap with sapropel");
        sapropel4.setType(sapropel4.getType());
        sapropel4.setImageUrl("https://naturatermospa.com/wp-content/uploads/2019/10/image-2.jpeg");
        sapropel4.setDescription("Sapropel – colloid structure (intermediate form between solid and liquid)" +
                " mud with healing properties, it is formed at the bottom of the standing bodies of fresh water." +
                "Sapropel with healing features – rich in organic and mineral substances, human body easily absorbs them." +
                " Lipids that it contains have antibacterial," +
                " antifungal, and anti-inflammatory properties, they also stimulate skin cells.");
        sapropel4.setPrice(28.00);

        this.sapropelRepository.save(sapropel4);
    }

    private void initSapropelProcedure3() {

        var sapropel3 = new SapropelProcedure();
        if (checkForExistingSapropel("Sapropel facial mask")) return;


        sapropel3.setName("Sapropel facial mask");
        sapropel3.setType(sapropel3.getType());
        sapropel3.setImageUrl("https://naturatermospa.com/wp-content/uploads/2019/10/image-5.jpeg");
        sapropel3.setDescription("Facial mask is intended for problem skin that is prone to acne." +
                " Sulphur that it contain gently dries and disinfects acne, bentonite clay and carbon acts in a" +
                " detoxifying way and cleans pores. Sapropel is the cleanest substance in ecological sense," +
                " it contains no toxic substances, gently cleans the skin," +
                " removes dead cells, activates blood flow, has antibacterial effect, and makes skin cells more elastic.");
        sapropel3.setPrice(36.00);

        this.sapropelRepository.save(sapropel3);
    }

    private void initSapropelProcedure2() {
    }

    private void initSapropelProcedure1() {
    }

    private boolean checkForExistingSapropel(String name) {
        Optional<SapropelProcedure> entityName = this.sapropelRepository.findByName(name);
        if (entityName.isPresent()) {
            System.out.println("This procedure exists");
            return true;
        }
        return false;
    }
}
