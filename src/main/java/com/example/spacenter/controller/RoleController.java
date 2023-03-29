package com.example.spacenter.controller;

import com.example.spacenter.model.dto.RoleDTO;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.UserRepository;
import com.example.spacenter.service.AuthService;
import com.example.spacenter.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
public class RoleController {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private AuthService authService;

    public RoleController(UserRepository userRepository, RoleService roleService, AuthService authService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.authService = authService;
    }

    @GetMapping("/change/role")
    public String add(Model model) {

        model.addAttribute("users", this.userRepository
                .findAll()
                .stream()
                .map(UserEntity::getEmail)
                .collect(Collectors.toList()));


        return "change-role";
    }


    @PostMapping("/change/role")
    public String addRoles(@ModelAttribute("roleAddBinding") RoleDTO roleDTO,
                           BindingResult result,
                           RedirectAttributes attributes) {

        this.roleService.addRoleToUser(roleDTO.getUsername(), roleDTO.getRole());
        attributes.addFlashAttribute("changed" , "User role was changed!");

        if (result.hasErrors()) {
            attributes.addFlashAttribute("roleDTO", roleDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.roleDTO",
                    result);
            return "redirect:/change-role";
        }

        return "redirect:/change/role";
    }

    @GetMapping("/delete/role")
    public String deleteRoles(RoleDTO roleDTO, Model model) {

        model.addAttribute("users", this.userRepository
                .findAll()
                .stream()
                .map(UserEntity::getEmail)
                .collect(Collectors.toList()));

        this.roleService.removeRoleFromUser(roleDTO.getUsername(), roleDTO.getRole());

        return "redirect:/home";
    }
}
