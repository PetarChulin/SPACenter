package com.example.spacenter.controller;

import com.example.spacenter.model.dto.CommentsDTO;
import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.repositories.CommentsRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentsController {

    private final CommentsRepository commentsRepository;
    private CommentService commentService;
    private SapropelRepository sapropelRepository;
    private LaserRepository laserRepository;

    public CommentsController(CommentsRepository commentsRepository, CommentService commentService, SapropelRepository sapropelRepository, LaserRepository laserRepository) {
        this.commentsRepository = commentsRepository;
        this.commentService = commentService;
        this.sapropelRepository = sapropelRepository;
        this.laserRepository = laserRepository;
    }

    @ModelAttribute("commentsDTO")
    public CommentsDTO commentsDTO() {
        return new CommentsDTO();
    }

    @GetMapping("/comment/add/sapropel/{id}")
    public String comments(Model model, @PathVariable Long id) {

        SapropelProcedure sapropelProcedure = this.sapropelRepository.findById(id).get();

        Long sId = sapropelProcedure.getId();
        model.addAttribute("sId", sId);


        return "SapropelProcedures/sapropel-comments";
    }

    @PostMapping("/comment/add/sapropel/{id}")
    public String commentSapropel(CommentsDTO commentsDTO, @PathVariable Long id) {

        this.commentService.createSapropelComment(commentsDTO, id);

        return "redirect:/all-comments";
    }

    @GetMapping("/comment/add/laser/{id}")
    public String commentsLaser(Model model, @PathVariable Long id) {

        LaserProcedure laserProcedure = this.laserRepository.findById(id).get();

        Long sId = laserProcedure.getId();
        model.addAttribute("sId", sId);


        return "LaserProcedures/laser-comments";
    }

    @PostMapping("/comment/add/laser/{id}")
    public String commentLaserAdd(CommentsDTO commentsDTO, @PathVariable Long id) {

        this.commentService.createLaserComment(commentsDTO, id);

        return "redirect:/all-comments";
    }

    @GetMapping("/comment/delete/{id}")
    public String commentDelete(Model model, @PathVariable Long id) {

        this.commentService.deleteComment(id);
        model.addAttribute("deleted", true);


        return "redirect:/all-comments";
    }

}
