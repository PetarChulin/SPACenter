package com.example.spacenter.controller;

import com.example.spacenter.model.dto.ChangePasswordDTO;
import com.example.spacenter.model.dto.RegisterUserDTO;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.UserRepository;
import com.example.spacenter.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

import static com.example.spacenter.service.CommonService.getUserId;


@Controller
public class UserProfileController {

    private final UserProfileService userProfileService;
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userProfileService = userProfileService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("changePasswordDTO")
    public ChangePasswordDTO changePasswordDTO() {
        return new ChangePasswordDTO();
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
                                 RedirectAttributes attributes,
                                 ChangePasswordDTO changePasswordDTO) {

        if(!changePasswordDTO.getPassword().equals(changePasswordDTO.getRepeatPassword())) {
            attributes.addFlashAttribute("mismatched", "Passwords mismatched");
        } else {

            Long userId = getUserId();
        this.userProfileService.updateUsername(userId , entity.getUsername());
            this.userProfileService.updatePassword(userId, passwordEncoder.encode(entity.getPassword()));
            attributes.addFlashAttribute("message", "Username and password was changed! Please re-login.");

        }
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
