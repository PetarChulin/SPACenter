package com.example.spacenter.controller;

import com.example.spacenter.model.dto.RegisterUserDTO;
import com.example.spacenter.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private AuthService authService;



    @Autowired
    public RegisterController(AuthService authService) {
        this.authService = authService;

    }

    @ModelAttribute("registerUserDTO")
    public RegisterUserDTO initUserDTO() {
        return new RegisterUserDTO();
    }



    @GetMapping("/register")
    public String register() {
        return "register";

    }

    @PostMapping("/register")
    public String register(@Valid RegisterUserDTO registerUserDTO,
                           BindingResult result,
                           RedirectAttributes attributes) {


        if (result.hasErrors() || !this.authService.register(registerUserDTO)) {
            attributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.registerUserDTO",
                    result);
            return "redirect:/register";
        }
        return "redirect:/home";

    }
}
