package com.example.spacenter.controller;

import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.UserRepository;
import com.example.spacenter.service.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

import static com.example.spacenter.service.MedicalSubProceduresService.getUserId;
import static com.example.spacenter.service.UserProfileService.getPrincipalName;

@Controller
public class UserProfileController {

    private final UserProfileService userProfileService;
    private UserRepository userRepository;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, UserRepository userRepository) {
        this.userProfileService = userProfileService;
        this.userRepository = userRepository;
    }


    @GetMapping("/change/username")
    public String changeView(Model model, @AuthenticationPrincipal UserDetails details) {

        String username = details.getUsername();
        Optional<UserEntity> user = userRepository.findByUsername(username);
        model.addAttribute("user", user);


        return "profile";
    }
    @PostMapping("/change/username")
    public String changeUsername(UserEntity entity,
                                 RedirectAttributes attributes) {

        Long userId = getUserId();

        this.userProfileService.updateUsername(userId , entity.getUsername());

        attributes.addFlashAttribute("message", "Username was changed! Please re-login.");

        return "redirect:/change/username";

    }

//    @PutMapping("{id}")
//    public ResponseEntity<UserEntity> updateUsername(@PathVariable Long id, @RequestBody UserEntity details) {
//        UserEntity user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
//
//        user.setUsername(details.getUsername());
//
//
//        return ResponseEntity.ok(user);
//    }


}
