package com.example.spacenter.controller;

import com.example.spacenter.model.entity.Counter;
import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.service.AuthService;
import com.example.spacenter.service.CartService;
import com.example.spacenter.service.MedicalSubProceduresService;
import com.example.spacenter.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static com.example.spacenter.service.MedicalSubProceduresService.getUserId;

@Controller
public class CartController {

    private SapropelRepository sapropelRepository;
    private MedicalSubProceduresService medicalSubProceduresService;

    private AuthService authService;

    private LoggedUser loggedUser;
    private CartService cartService;
    private LaserRepository laserRepository;

    public CartController(SapropelRepository sapropelRepository, MedicalSubProceduresService medicalSubProceduresService, AuthService authService, LoggedUser loggedUser, CartService cartService, LaserRepository laserRepository) {
        this.sapropelRepository = sapropelRepository;
        this.medicalSubProceduresService = medicalSubProceduresService;
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.cartService = cartService;
        this.laserRepository = laserRepository;
    }

    @GetMapping("/sapropel/buy/{id}")
    public String buySapropel(@PathVariable Long id) {

//        if (medicalSubProceduresService.inCart(id)) {
//            return "redirect:/already-bought";
//        }
        medicalSubProceduresService.addSapropelToCart(id);

        return "redirect:/cart";

    }

    @GetMapping("/sapropel/delete/{id}")
    public String removeSapropel(@PathVariable Long id) {

        medicalSubProceduresService.deleteSapropelFromCart(id);

        return "redirect:/cart";
    }

    @GetMapping("/laser/buy/{id}")
    public String buyLaser(@PathVariable Long id) {

        medicalSubProceduresService.addLaserToCart(id);

        return "redirect:/cart";
    }

    @GetMapping("/laser/delete/{id}")
    public String removeLaser(@PathVariable Long id) {

        medicalSubProceduresService.deleteLaserFromCart(id);

        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showOrders(Model model) {

        Long userId = getUserId();

        List<SapropelProcedure> sapropelOrders = this.sapropelRepository.findByBuyers_Id(userId);
        List<LaserProcedure> laserOrders = this.laserRepository.findByBuyers_Id(userId);

        List<Object> allOrders = new ArrayList<>();
        allOrders.addAll(sapropelOrders);
        allOrders.addAll(laserOrders);


        int countOfSapropelOrders = sapropelOrders.size();
        int countOfAllOrders = allOrders.size();
        Double totalPrice = this.cartService.getTotalOrderPrice(userId);

        model.addAttribute("countOfSapropelOrders", countOfSapropelOrders);
        model.addAttribute("countOfAllOrders", countOfAllOrders);
        model.addAttribute("sapropelOrders", sapropelOrders);
        model.addAttribute("laserOrders" , laserOrders);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("counter", new Counter());
        model.addAttribute("allOrders" , allOrders);

        return "cart";
    }

}
