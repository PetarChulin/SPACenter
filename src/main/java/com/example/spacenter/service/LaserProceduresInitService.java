package com.example.spacenter.service;

import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LaserProceduresInitService {

    private final LaserRepository laserRepository;

    public LaserProceduresInitService(LaserRepository laserRepository) {
        this.laserRepository = laserRepository;
    }

    @PostConstruct
    private void initLaserProcedures() {
        initLaserProcedure();
        initLaserProcedure2();
        initLaserProcedure3();
        initLaserProcedure4();
    }


    void initLaserProcedure() {

        var laser = new LaserProcedure();
        if (checkForExistingLaser("Carboxytherapy – facial treatment")) return;

        laser.setId((long) Math.floor(Math.random() * 44));
        laser.setName("Carboxytherapy – facial treatment");
        laser.setType(laser.getType());
        laser.setImageUrl("/images/laser/image-61.jpeg");
        laser.setDescription("During the treatment, blood circulation is restored in capillaries (haemoglobin releases more oxygen)," +
                " collagen production is facilitated and the moisture balance of the epidermis is restored. It suitable not only for" +
                " the face, but the body as well." +
                " The price of the procedure may vary according to the size of the area which is treated by carboxytherapy." +
                "Carboxytherapy– a treatment during which the hypoderm is infused with the necessary amount of carbon dioxide (CO2)." +
                " Then the vascular function strongly intensifies," +
                " the blood vessels expand – thus ensuring higher blood flow to the muscle and fat tissues.");
        laser.setPrice(30.00);

        this.laserRepository.save(laser);
    }

    private void initLaserProcedure2() {

        var laser2 = new LaserProcedure();
        if (checkForExistingLaser("Laser acne treatment")) return;

        laser2.setId((long) Math.floor(Math.random() * 51));
        laser2.setName("Laser acne treatment");
        laser2.setType(laser2.getType());
        laser2.setImageUrl("/images/laser/lazeris.jpg");
        laser2.setDescription("Treatment of acne and pimples can be easy and effective with" +
                " the help of modern laser treatment technologies. During the laser treatment," +
                " bacteria are eliminated, skin inflammation is reduced, and the skin becomes" +
                " smoother and healthier with fewer scars and imperfections." +
                " Usually, only 2-3 treatments are needed, and the effect is long-term." +
                "Treatment of acne (pimples) can be easier and more effective with the help of modern laser technologies." +
                " This treatment is based on the laser beam that effectively eliminates bacteria that cause rash and pimples." +
                " Laser light has anti-inflammatory properties and promotes body healing processes, so not only pimples disappear," +
                " but the healing of scars left after acne becomes faster." +
                " The skin looks healthier and smoother after the treatments.");
        laser2.setPrice(40.00);

        this.laserRepository.save(laser2);
    }

    private void initLaserProcedure3() {

        var laser3 = new LaserProcedure();
        if (checkForExistingLaser("Laser wart treatment")) return;

        laser3.setId((long) Math.floor(Math.random() * 150));
        laser3.setName("Laser wart treatment");
        laser3.setType(laser3.getType());
        laser3.setImageUrl("/images/laser/karp.jpg");
        laser3.setDescription("Laser wart treatment is a safe and effective treatment that" +
                " does not cause any additional side effect and do not affect the nearby tissues." +
                " Laser treatment can also be used successfully to remove papillomas," +
                " molluscs, condylomas or other skin formations." +
                "Even though there are many wart treatment methods, modern laser treatment is one of the fastest," +
                " safest and most effective ways of treating different types of warts such as papillomas, condylomas and other skin formations." +
                " This treatment is also effective on warts that are resistant to medication," +
                " and they can be removed from any part of the body.");
        laser3.setPrice(25.00);

        this.laserRepository.save(laser3);
    }

    public void initLaserProcedure4(){

        var laser4 = new LaserProcedure();
        if (checkForExistingLaser("Mesotherapy – facial treatment")) return;

        laser4.setId((long) Math.floor(Math.random() * 250));
        laser4.setName("Mesotherapy – facial treatment");
        laser4.setType(laser4.getType());
        laser4.setImageUrl("/images/laser/mesotherapy.jpeg");
        laser4.setDescription("This is a procedure during which small amount of active substances is injected under the skin." +
                "This prevents aging processes, restores skin elasticity and firmness.");
        laser4.setPrice(60.00);

        this.laserRepository.save(laser4);
    }


    boolean checkForExistingLaser(String name) {
        Optional<LaserProcedure> entityName = this.laserRepository.findByName(name);
        if (entityName.isPresent()) {
            System.out.println("This procedure exists");
            return true;
        }
        return false;
    }
}
