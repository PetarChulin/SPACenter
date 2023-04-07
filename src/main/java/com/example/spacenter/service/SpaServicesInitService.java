package com.example.spacenter.service;

import com.example.spacenter.model.entity.SpaProcedures.SpaServices;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpaServicesInitService {

    private SpaServicesRepository spaServicesRepository;

    public SpaServicesInitService(SpaServicesRepository spaServicesRepository) {
        this.spaServicesRepository = spaServicesRepository;
    }

    @PostConstruct
    public void spaServicesInitProcedures() {
        initSpaService1();
        initSpaService2();

    }

    private void initSpaService1() {

        var service1 = new SpaServices();
        if (checkForExistingService("Classic hammam procedure")) return;

        service1.setId((long) Math.floor(Math.random() * 250));
        service1.setName("Classic hammam procedure");
        service1.setType(service1.getType());
        service1.setImageUrl("/images/spa_center/hammam2.jpg");
        service1.setDescription("A classic hammam treatment:\n" +
                "\n" +
                "    During the Hammam treatment the pores of the skin open up," +
                " the body is ready to accept the soap massage, during which you will be covered in a true cloud of soap foam." +
                " After the hammam treatment a positive effect to your body is felt: circulatory, lymphatic," +
                " respiratory and nervous systems. On the heated marble beds, the most pleasant treatments are performed on the body:" +
                " skin surface scrub and wash. The wash treatment with soap foam is coordinated with the massage." +
                " Your body becomes light, and the skin – silky soft.\n" +
                "\n" +
                "Treatment consists of:\n" +
                "\n" +
                "    body scrub and a massage with the „KESE“ glove;\n" +
                "    Turkish bath and massage treatments are combined with foam from soap.\n" +
                "\n" +
                " \n" +
                "\n" +
                "The treatment is performed in the water and sauna area, on the ground floor, with the" +
                " “Atostogų parkas” soap from the healing properties of mud – sapropel.\n" +
                "\n" +
                "Duration: 30 min." +

                "Duration: 60 min.");
        service1.setPrice(39.00);

        this.spaServicesRepository.save(service1);
    }

    private void initSpaService2() {

        var service2 = new SpaServices();
        if (checkForExistingService("Classical Hammam Procedure with Jacuzzi")) return;

        service2.setId((long) Math.floor(Math.random() * 255));
        service2.setName("Classical Hammam Procedure with Jacuzzi");
        service2.setType(service2.getType());
        service2.setImageUrl("/images/spa_center/hammam2.jpg");
        service2.setDescription("A classic hammam treatment:\n" +
                "\n" +
                "    During the Hammam treatment the pores of the skin open up," +
                " the body is ready to accept the soap massage, during which you will be covered in a true cloud of soap foam." +
                " After the hammam treatment a positive effect to your body is felt: circulatory, lymphatic," +
                " respiratory and nervous systems. On the heated marble beds, the most pleasant treatments are performed on the body:" +
                " skin surface scrub and wash. The wash treatment with soap foam is coordinated with the massage." +
                " Your body becomes light, and the skin – silky soft.\n" +
                "\n" +
                "Treatment consists of:\n" +
                "\n" +
                "    body scrub and a massage with the „KESE“ glove;\n" +
                "    Turkish bath and massage treatments are combined with foam from soap.\n" +
                "\n" +
                " \n" +
                "\n" +
                "The treatment is performed in the water and sauna area, on the ground floor, with the" +
                " “Atostogų parkas” soap from the healing properties of mud – sapropel.\n" +
                "\n" +
                "Duration: 30 min." +

                "Duration: 60 min.");
        service2.setPrice(49.00);

        this.spaServicesRepository.save(service2);
    }

    private boolean checkForExistingService(String name) {
        Optional<SpaServices> entityName = this.spaServicesRepository.findByName(name);
        if (entityName.isPresent()) {
            System.out.println("This procedure exists");
            return true;
        }
        return false;
    }

}
