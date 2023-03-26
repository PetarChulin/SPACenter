package com.example.spacenter.controller;

import com.example.spacenter.model.dto.RegisterUserDTO;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.UserRepository;
import com.example.spacenter.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class RegisterController {

    private AuthService authService;
    private UserRepository userRepository;


    @Autowired
    public RegisterController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;

        this.userRepository = userRepository;
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
        Optional<UserEntity> email = this.userRepository.findByEmail(registerUserDTO.getEmail());
        Optional<UserEntity> username = this.userRepository.findByUsername(registerUserDTO.getUsername());

        if(email.isPresent()) {
            attributes.addFlashAttribute("existedEmail", true);
        }

        if(username.isPresent()) {
            attributes.addFlashAttribute("existedName", true);
        }

        if(!registerUserDTO.getPassword().equals(registerUserDTO.getRepeatPassword())) {
            attributes.addFlashAttribute("mismatched" , true);
        }

        if (result.hasErrors() || !this.authService.register(registerUserDTO)) {
            attributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.registerUserDTO",
                    result);

            return "redirect:/register";
        }

        return "redirect:/home";

    }
}
