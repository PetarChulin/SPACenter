package com.example.spacenter.controller;

import com.example.spacenter.model.entity.Comment;
import com.example.spacenter.repositories.CommentsRepository;
import com.example.spacenter.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CommentsViewController {

    private CommentsRepository commentsRepository;

    private CommentService commentService;

    public CommentsViewController(CommentsRepository commentsRepository,
                                  CommentService commentService) {
        this.commentsRepository = commentsRepository;
        this.commentService = commentService;
    }

    @GetMapping("/all-comments")
    public String allComments(Model model, RedirectAttributes attributes) {

        List<Comment> comments = this.commentsRepository.findAll();
        model.addAttribute("comments", comments);
        attributes.addFlashAttribute("noComments", "There are no comments to show");



        return "all-comments";
    }


//        @GetMapping(path = "/all-comments")
//    public ResponseEntity<List<Comment>> getAllComments() {
//        var comments = this.commentsRepository.findAll();
//
//        return ResponseEntity.ok(comments);
//    }

}
