package com.example.spacenter.controller;

import com.example.spacenter.model.entity.Comment;
import com.example.spacenter.repositories.CommentsRepository;
import com.example.spacenter.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MedicalCommentsViewController {

    private CommentsRepository commentsRepository;

    private CommentService commentService;

    public MedicalCommentsViewController(CommentsRepository commentsRepository,
                                         CommentService commentService) {
        this.commentsRepository = commentsRepository;
        this.commentService = commentService;
    }

    @GetMapping("/all-comments")
    public String sapropelComments(Model model) {

        List<Comment> comments = this.commentsRepository.findAll();


        model.addAttribute("comments", comments);


        return "all-comments";
    }
}
