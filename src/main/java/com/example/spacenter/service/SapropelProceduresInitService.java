package com.example.spacenter.service;

import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SapropelProceduresInitService {

    private SapropelRepository sapropelRepository;
    private LaserRepository laserRepository;
    private SpaRitualsRepository spaRitualsRepository;

    public SapropelProceduresInitService(SapropelRepository sapropelRepository, LaserRepository laserRepository, SpaRitualsRepository spaRitualsRepository) {
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
        initSapropelProcedure5();
    }

    private void initSapropelProcedure5() {

        var sapropel5 = new SapropelProcedure();
        if (checkForExistingSapropel("Sapropel mask (for the back/ joints)")) return;

        sapropel5.setId((long) Math.floor(Math.random() * 50));
        sapropel5.setName("Sapropel mask (for the back/ joints)");
        sapropel5.setType(sapropel5.getType());
        sapropel5.setImageUrl("/images/sapropel/image-4.jpeg");
        sapropel5.setDescription("Vitamins of the B group that are in the sapropel, participate" +
                " in the processes of metabolism, help to maintain healthy skin and muscle tone," +
                " strengthen functions of the immune and neural systems, as well as promote cell growth and division." +
                " These vitamins help to defeat anaemia, as well as symptoms and causes of stress and depression." +
                " Bromine, iodine, phosphorus, and magnesium that are in the Sapropel have anaesthetic" +
                " and calming effect on the neural tissue," +
                " restores working capacity of the body after the emotional and physical fatigue.");
        sapropel5.setPrice(14.00);

        this.sapropelRepository.save(sapropel5);
    }

    private void initSapropelProcedure4() {

        var sapropel4 = new SapropelProcedure();
        if (checkForExistingSapropel("Full body wrap with sapropel")) return;

        sapropel4.setId((long) Math.floor(Math.random() * 60));
        sapropel4.setName("Full body wrap with sapropel");
        sapropel4.setType(sapropel4.getType());
        sapropel4.setImageUrl("/images/sapropel/image-2.jpeg");
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

        sapropel3.setId((long) Math.floor(Math.random() * 70));
        sapropel3.setName("Sapropel facial mask");
        sapropel3.setType(sapropel3.getType());
        sapropel3.setImageUrl("/images/sapropel/image-5.jpeg");
        sapropel3.setDescription("Facial mask is intended for problem skin that is prone to acne." +
                " Sulphur that it contain gently dries and disinfects acne, bentonite clay and carbon acts in a" +
                " detoxifying way and cleans pores. Sapropel is the cleanest substance in ecological sense," +
                " it contains no toxic substances, gently cleans the skin," +
                " removes dead cells, activates blood flow, has antibacterial effect, and makes skin cells more elastic.");
        sapropel3.setPrice(36.00);

        this.sapropelRepository.save(sapropel3);
    }

    private void initSapropelProcedure2() {

        var sapropel2 = new SapropelProcedure();
        if (checkForExistingSapropel("Sapropel mask for hair")) return;

        sapropel2.setId((long) Math.floor(Math.random() * 10));
        sapropel2.setName("Sapropel mask for hair");
        sapropel2.setType(sapropel2.getType());
        sapropel2.setImageUrl("/images/sapropel/image-3.jpeg");
        sapropel2.setDescription("Every woman dreams about long and healthy hair. “Vacation park” SPA" +
                " offers elder tested recipes. Sapropel mask for hair is exclusive, because it is unique," +
                " ecological, uses the most natural substance," +
                " contains no toxins, is not contaminated with heavy metals and radioactive nuclides.");
        sapropel2.setPrice(26.00);

        this.sapropelRepository.save(sapropel2);
    }

    private void initSapropelProcedure1() {

        var sapropel1 = new SapropelProcedure();
        if (checkForExistingSapropel("Paraffin bath for feet")) return;

        sapropel1.setId((long) Math.floor(Math.random() * 500));
        sapropel1.setName("Paraffin bath for feet");
        sapropel1.setType(sapropel1.getType());
        sapropel1.setImageUrl("/images/sapropel/parafino-1.jpg");
        sapropel1.setDescription("Light scrub and massage of feet are performed before paraffin therapy." +
                " After that, the feet are immersed in paraffin. It contains many useful materials." +
                " The temperature of the feet skin coated with the serum raises by one or two degrees," +
                " and the paraffin layer does not allow the skin to cool.\n" +
                "Therefore, the horny layer softens, the pairs open, all the active" +
                " substances penetrate into the deep layers. Warmth causes the skin to sweat," +
                " thus eliminating toxins. This procedure is effective for a week. Physicians recommend it to anyone with a circulatory disorder. Particularly it is useful for those who feel cold in limbs." +
                " Paraffin therapy also helps to relieve tension and anxiety.");
        sapropel1.setPrice(26.00);

        this.sapropelRepository.save(sapropel1);
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
