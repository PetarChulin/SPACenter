package com.example.spacenter.controller;

import com.example.spacenter.model.dto.CommentsDTO;
import com.example.spacenter.model.entity.BaseProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.model.entity.SpaProcedures.SpaServices;
import com.example.spacenter.repositories.CommentsRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
import com.example.spacenter.service.CommentService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static org.bouncycastle.asn1.x500.style.BCStyle.T;

@Controller
public class CommentsController {

    private final CommentsRepository commentsRepository;
    private CommentService commentService;
    private SapropelRepository sapropelRepository;
    private LaserRepository laserRepository;

    private SpaRitualsRepository spaRitualsRepository;

    private SpaServicesRepository spaServicesRepository;

    public CommentsController(CommentsRepository commentsRepository,
                              CommentService commentService,
                              SapropelRepository sapropelRepository,
                              LaserRepository laserRepository,
                              SpaRitualsRepository spaRitualsRepository,
                              SpaServicesRepository spaServicesRepository) {
        this.commentsRepository = commentsRepository;
        this.commentService = commentService;
        this.sapropelRepository = sapropelRepository;
        this.laserRepository = laserRepository;
        this.spaRitualsRepository = spaRitualsRepository;
        this.spaServicesRepository = spaServicesRepository;
    }

    @ModelAttribute("commentsDTO")
    public CommentsDTO commentsDTO() {
        return new CommentsDTO();
    }

    @GetMapping("/comment/add/sapropel/{id}")
    public String comments(Model model, @PathVariable Long id) {

        SapropelProcedure procedure = this.sapropelRepository.findById(id).get();

        Long sId = procedure.getId();
        String type = procedure.getType();
        model.addAttribute("sId", sId);
        model.addAttribute("type", type);


        return "add-comments";
    }

    @PostMapping("/comment/add/sapropel/{id}")
    public String commentSapropel(CommentsDTO commentsDTO, @PathVariable Long id) {

        this.commentService.createSapropelComment(commentsDTO, id);

        return "redirect:/all-comments";
    }

    @GetMapping("/comment/add/laser/{id}")
    public String commentsLaser(Model model, @PathVariable Long id) {

        LaserProcedure procedure = this.laserRepository.findById(id).get();

        Long sId = procedure.getId();
        String type = procedure.getType();
        model.addAttribute("sId", sId);
        model.addAttribute("type", type);

        return "add-comments";
    }

    @PostMapping("/comment/add/laser/{id}")
    public String commentLaserAdd(CommentsDTO commentsDTO, @PathVariable Long id) {

        this.commentService.createLaserComment(commentsDTO, id);

        return "redirect:/all-comments";
    }

    @GetMapping("/comment/add/spa-rituals/{id}")
    public String commentsSpaRitual(Model model, @PathVariable Long id) {

        SpaRituals procedure = this.spaRitualsRepository.findById(id).get();

        Long sId = procedure.getId();
        String type = procedure.getType();
        model.addAttribute("sId", sId);
        model.addAttribute("type", type);

        return "add-comments";
    }

    @PostMapping("/comment/add/spa-rituals/{id}")
    public String commentSpaRitual(CommentsDTO commentsDTO, @PathVariable Long id) {

        this.commentService.createSpaRitualComment(commentsDTO, id);

        return "redirect:/all-comments";
    }

    @GetMapping("/comment/add/spa-services/{id}")
    public String commentsSpaService(Model model, @PathVariable Long id) {

        SpaServices procedure = this.spaServicesRepository.findById(id).get();
        Long sId = procedure.getId();
        String type = procedure.getType();
        model.addAttribute("sId", sId);
        model.addAttribute("type", type);

        return "add-comments";
    }

    @PostMapping("/comment/add/spa-services/{id}")
    public String commentSpaService(CommentsDTO commentsDTO, @PathVariable Long id) {

        this.commentService.createSpaServiceComment(commentsDTO, id);

        return "redirect:/all-comments";
    }



        @GetMapping("/comment/delete/{id}")
    public String commentDelete(Model model, @PathVariable Long id) {

        this.commentService.deleteComment(id);
        model.addAttribute("deleted", true);


        return "redirect:/all-comments";
    }

}
