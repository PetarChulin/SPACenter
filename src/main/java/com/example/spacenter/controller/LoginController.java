package com.example.spacenter.controller;

import com.example.spacenter.model.dto.LoginUserDTO;
import com.example.spacenter.repositories.UserRepository;
import com.example.spacenter.service.AuthService;
import com.example.spacenter.session.LoggedUser;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LoginController {

    private AuthService authService;

    private LoggedUser session;
    private UserRepository userRepository;

    public LoginController(AuthService authService, LoggedUser session, UserRepository userRepository) {
        this.authService = authService;
        this.session = session;
        this.userRepository = userRepository;
    }



    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login-error")
    public String login(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            BindingResult result,
            RedirectAttributes attributes) {

        attributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        attributes.addFlashAttribute("badCredentials", true);

        if (result.hasErrors()) {
            attributes.addFlashAttribute("username", username);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.username",
                    result);
            return "redirect:/login";
        }

        return "redirect:/login";


    }

//    @PostMapping("/login")
//    public String login(@Valid LoginUserDTO loginUserDTO,
//                        BindingResult result,
//                        RedirectAttributes attributes) {
//
//        if (this.authService.isLoggedIn()) {
//            return "redirect:/";
//        }
//        Optional<UserEntity> user = this.userRepository.findByUsername(loginUserDTO.getUsername());
//        if (this.authService.authenticate(loginUserDTO.getUsername(), loginUserDTO.getPassword())) {
//            this.session.setId(user.get().getId());
//            this.session.setUsername(user.get().getUsername());
//            this.session.setRole(user.get().getRole().toString());
//
////            return "redirect:/medical-procedures";
//        }
//
//
//
//        if (result.hasErrors()) {
//            attributes.addFlashAttribute("loginUserDTO", loginUserDTO);
//            attributes.addFlashAttribute("org.springframework.validation.BindingResult.loginUserDTO",
//                    result);
//            return "redirect:/login";
//        }
//
//        if (!this.authService.login(loginUserDTO)) {
//            attributes.addFlashAttribute("loginUserDTO", loginUserDTO);
//            attributes.addFlashAttribute("badCredentials",
//                    true);
//            return "redirect:/login";
//        }
//
//
//        return "redirect:/home";
//
//
//    }
//
//    @GetMapping("/logout")
//    public String logout() {
//        this.authService.logout();
//
//        return "redirect:/";
//    }
}
