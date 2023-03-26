package com.example.spacenter.controller;

import com.example.spacenter.model.entity.Counter;
import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static com.example.spacenter.service.CommonService.getUserId;
import static com.example.spacenter.service.CommonService.inCart;

@Controller
public class CartController {

    private SapropelRepository sapropelRepository;
    private LaserRepository laserRepository;
    private SpaRitualsRepository spaRitualsRepository;

    private CartService cartService;

    public CartController(SapropelRepository sapropelRepository, LaserRepository laserRepository,
                          SpaRitualsRepository spaRitualsRepository, CartService cartService) {
        this.sapropelRepository = sapropelRepository;
        this.laserRepository = laserRepository;
        this.spaRitualsRepository = spaRitualsRepository;
        this.cartService = cartService;
    }


    @GetMapping("/cart")
    public String showOrders(Model model) {

        Long userId = getUserId();

        List<SapropelProcedure> sapropelOrders = this.sapropelRepository.findByBuyers_Id(userId);
        List<LaserProcedure> laserOrders = this.laserRepository.findByBuyers_Id(userId);
        List<SpaRituals> spaRitualsOrders = this.spaRitualsRepository.findByBuyers_Id(userId);

        List<Object> allOrders = new ArrayList<>();
        allOrders.addAll(sapropelOrders);
        allOrders.addAll(laserOrders);
        allOrders.addAll(spaRitualsOrders);


        int countOfSapropelOrders = sapropelOrders.size();
        int countOfAllOrders = allOrders.size();
//        Double totalPrice = this.cartService.getTotalOrderPrice(userId);

        model.addAttribute("countOfSapropelOrders", countOfSapropelOrders);
        model.addAttribute("countOfAllOrders", countOfAllOrders);
        model.addAttribute("sapropelOrders", sapropelOrders);
        model.addAttribute("laserOrders", laserOrders);
        model.addAttribute("spaRitualsOrders", spaRitualsOrders);
//        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("counter", new Counter());
        model.addAttribute("allOrders", allOrders);

        return "cart";
    }

    @GetMapping("/delete/all")
    public String deleteAllFromCart(RedirectAttributes attributes) {

        this.cartService.deleteAllFromUserCart();


        return "redirect:/cart";
    }

    public static void checkForAvailability(RedirectAttributes attributes) {
        if (inCart) {
            attributes.addFlashAttribute("alreadyInCart", true);
        } else {
            attributes.addFlashAttribute("added", true);
        }
    }
}
