package com.example.spacenter.controller;

import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.service.AuthService;
import com.example.spacenter.service.CartService;
import com.example.spacenter.service.MedicalSubProceduresService;
import com.example.spacenter.service.SpaSubProceduresService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.example.spacenter.controller.CartController.checkForAvailability;

@Controller
public class AllOrdersController {

    private SapropelRepository sapropelRepository;
    private MedicalSubProceduresService medicalSubProceduresService;

    private AuthService authService;

    private CartService cartService;
    private LaserRepository laserRepository;

    private SpaRitualsRepository spaRitualsRepository;

    private SpaSubProceduresService spaSubProceduresService;

    public AllOrdersController(SapropelRepository sapropelRepository, MedicalSubProceduresService medicalSubProceduresService, AuthService authService,  CartService cartService, LaserRepository laserRepository, SpaRitualsRepository spaRitualsRepository, SpaSubProceduresService spaSubProceduresService) {
        this.sapropelRepository = sapropelRepository;
        this.medicalSubProceduresService = medicalSubProceduresService;
        this.authService = authService;
        this.cartService = cartService;
        this.laserRepository = laserRepository;
        this.spaRitualsRepository = spaRitualsRepository;
        this.spaSubProceduresService = spaSubProceduresService;
    }

    @GetMapping("/sapropel/buy/{id}")
    public String buySapropel(@PathVariable Long id, RedirectAttributes attributes) {

        medicalSubProceduresService.addSapropelToCart(id);
        checkForAvailability(attributes);

        return "redirect:/SapropelProcedures/sapropel-procedures";

    }


    @GetMapping("/sapropel/delete/{id}")
    public String removeSapropel(@PathVariable Long id, RedirectAttributes attributes) {

        medicalSubProceduresService.deleteSapropelFromCart(id);
        attributes.addFlashAttribute("deleted" , true);

        return "redirect:/cart";
    }

    @GetMapping("/laser/buy/{id}")
    public String buyLaser(@PathVariable Long id, RedirectAttributes attributes) {

        medicalSubProceduresService.addLaserToCart(id);
        checkForAvailability(attributes);
        return "redirect:/LaserProcedures/laser-procedures";
    }

    @GetMapping("/laser/delete/{id}")
    public String removeLaser(@PathVariable Long id, RedirectAttributes attributes) {

        medicalSubProceduresService.deleteLaserFromCart(id);
        attributes.addFlashAttribute("deleted" , true);

        return "redirect:/cart";
    }

    @GetMapping("/spa-rituals/buy/{id}")
    public String buySpaRitual(@PathVariable Long id, RedirectAttributes attributes) {

        spaSubProceduresService.addSpaRitualToCart(id);
        checkForAvailability(attributes);

        return "redirect:/SPARituals/spa-rituals";
    }

    @GetMapping("/spa-rituals/delete/{id}")
    public String removeSpaRituals(@PathVariable Long id, RedirectAttributes attributes) {

        spaSubProceduresService.deleteSpaRitualFromCart(id);
        attributes.addFlashAttribute("deleted" , true);

        return "redirect:/cart";
    }

    @GetMapping("/spa-services/buy/{id}")
    public String buySpaService(@PathVariable Long id, RedirectAttributes attributes) {

        spaSubProceduresService.addSpaServiceToCart(id);
        checkForAvailability(attributes);

        return "redirect:/SPACenter/spa-center";
    }

    @GetMapping("/spa-services/delete/{id}")
    public String removeSpaService(@PathVariable Long id, RedirectAttributes attributes) {

        spaSubProceduresService.deleteSpaServiceFromCart(id);
        attributes.addFlashAttribute("deleted" , true);

        return "redirect:/cart";
    }
}
