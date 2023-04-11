package com.example.spacenter.service;

import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpaRitualsInitService {

    private final SpaRitualsRepository spaRitualsRepository;

    public SpaRitualsInitService(SpaRitualsRepository spaRitualsRepository) {
        this.spaRitualsRepository = spaRitualsRepository;
    }

    @PostConstruct
    public void spaRitualsInitProcedures() {
            initSpaRitual1();
            initSpaRitual2();
            initSpaRitual3();
            initSpaRitual4();
    }

    void initSpaRitual1() {

        var ritual1 = new SpaRituals();
        if (checkForExistingRitual("Amber pint massage")) return;

        ritual1.setId((long) Math.floor(Math.random() * 150));
        ritual1.setName("Amber pint massage");
        ritual1.setType(ritual1.getType());
        ritual1.setImageUrl("/images/spa_rituals/amber.jpeg");
        ritual1.setDescription("The products used during the amber pint massage the products used are made" +
                " from natural amber: amber oil," +
                " amber powder, warm amber pints, amber incense, amber tea." +
                "If stress is your constant companion– relax and enjoy the full body Amber pint massage." +
                " You will like the technology of this massage: a slow massaging pace will increase in time," +
                " massaged surface tissue will go deeper within. Amber oil and amber powder will facilitate tissue" +
                " regeneration, improve the appearance of your skin and positively influence your emotional state." +
                " Your body and soul will for a moment fade from reality and once you come back," +
                " you will feel like a brand new person.\n" +

                "Duration: 60 min.");
        ritual1.setPrice(89.00);

        this.spaRitualsRepository.save(ritual1);
    }

    private void initSpaRitual2() {

        var ritual2 = new SpaRituals();
        if (checkForExistingRitual("Classic back massage")) return;

        ritual2.setId((long) Math.floor(Math.random() * 140));
        ritual2.setName("Classic back massage");
        ritual2.setType(ritual2.getType());
        ritual2.setImageUrl("/images/spa_rituals/classic.jpeg");
        ritual2.setDescription("The Classic back massage relieves tense muscles of the back and shoulders," +
                " reduces pain, helps to calm down, and to forget everyday concerns." +
                " Following the massage you will feel rested as the tension lifts," +
                " thoughts become clearer and sleep gets better." +
                "Lighter body, relaxation and light thoughts, like summer clouds in the sky… " +
                "This is how our clients feel after the classic back massage. Give yourself half an hour break" +
                " to rest your body and mind in this fast-moving work and flood of information.\n" +
                "\n" +
                "When you are free from the tension and stress, you will be able to return to your daily" +
                " life full of new strength. After reducing tension in the back, waist and shoulders," +
                " you can relax your body and thoughts, improve your concentration, sleep and mood.\n" +
                "\n" +
                "Duration: 30 min.");
        ritual2.setPrice(31.00);

        this.spaRitualsRepository.save(ritual2);
    }

    private void initSpaRitual3() {

        var ritual3 = new SpaRituals();
        if (checkForExistingRitual("Classic manicure")) return;

        ritual3.setId((long) Math.floor(Math.random() * 150));
        ritual3.setName("Classic manicure");
        ritual3.setType(ritual3.getType());
        ritual3.setImageUrl("/images/spa_rituals/manicure.jpeg");
        ritual3.setDescription("Hygienic manicure is a special treatment for the skin and nails of the hands, during which a manicure," +
                " nail varnishing and a hand massage is performed, and the hands are treated with wonderful masks and creams." +
                "Well-groomed hands and a perfect manicure are the pride of every woman," +
                " and long lasting (gel) nail polish is a real salvation for women who do not have enough" +
                " time to take care of their nails and hands every day." +
                " You can choose standard or gel* polish.\n" +
                "\n" +
                "During manicure you can order additional services: paraffin bath for hands.\n" +
                "\n" +
                "*Long-term (gel) varnishing +10 €. The gel polish differs from ordinary nail polish because" +
                " it hardens in ultraviolet light and lasts up to 2-3 weeks.\n" +
                "\n" +
                "Duration of treatment: 60 min.");
        ritual3.setPrice(18.00);

        this.spaRitualsRepository.save(ritual3);
    }

    private void initSpaRitual4() {

        var ritual4 = new SpaRituals();
        if (checkForExistingRitual("Classic pedicure")) return;

        ritual4.setId((long) Math.floor(Math.random() * 145));
        ritual4.setName("Classic pedicure");
        ritual4.setType(ritual4.getType());
        ritual4.setImageUrl("/images/spa_rituals/pedicure.jpeg");
        ritual4.setDescription("he classic pedicure gently nurtures foot skin and foot nails. This procedure, performed regularly," +
                " will not only delight the beautiful appearance of the feet and nails, but will also ensure their health." +
                "During the classic pedicure, the feet are soaked in warm water, usually with dissolved sea salt or other ingredients for the pedicure. Later, the hard layer of skin is removed, coticules is cut around the nails, the nail shape is corrected. Nails are covered with nail polish.\n" +
                "\n" +
                "It is recommended to perform a classic pedicure once every 1-2 months. It is recommended to choose an additional service – long-lasting (hybrid) varnish.\n" +
                "\n" +
                "Duration of treatment: 60 min.");
        ritual4.setPrice(49.00);

        this.spaRitualsRepository.save(ritual4);
    }

    private boolean checkForExistingRitual(String name) {
        Optional<SpaRituals> entityName = this.spaRitualsRepository.findByName(name);
        if (entityName.isPresent()) {
            System.out.println("This procedure exists");
            return true;
        }
        return false;
    }
}
