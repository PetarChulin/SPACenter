package com.example.spacenter.controller;

import com.example.spacenter.model.entity.Comment;
import com.example.spacenter.repositories.CommentsRepository;
import com.example.spacenter.service.CommentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CommentsViewController {

    private final CommentsRepository commentsRepository;

    private final CommentService commentService;

    private RestTemplate restTemplate;

    public CommentsViewController(CommentsRepository commentsRepository,
                                  CommentService commentService, RestTemplate restTemplate) {
        this.commentsRepository = commentsRepository;
        this.commentService = commentService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/all-comments")
    public String allComments(Model model, RedirectAttributes attributes) {


        List<Comment> comments = this.commentsRepository.findAll();

        model.addAttribute("comments", comments);
        if (comments.isEmpty()) {
            attributes.addFlashAttribute("noComments", "There are no comments to show");
        }
        return "all-comments";
    }

    @GetMapping("/comments-byProcedureName/{id}")
    public String commentsById(Model model, @PathVariable Long id) {


        List<Comment> commentsByProcedureId = commentService.showCommentsByProcedureId(id);
        String name = commentService.findNameById(id);

        model.addAttribute("commentsByProcedureId", commentsByProcedureId);
        model.addAttribute("name" , name);


        return "comments-byProcedureName";
    }

//    @RequestMapping(value = "/all-comments")
//    public String getComments() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//        HttpEntity<Comment> entity = new HttpEntity<>(headers);
//
//        return restTemplate.exchange("http://localhost:8080/all-comments", HttpMethod.GET, entity, String.class).getBody();
//    }


//        @GetMapping(path = "/all-comments")
//    public ResponseEntity<List<Comment>> getAllComments() {
//        var comments = this.commentsRepository.findAll();
//
//        return ResponseEntity.ok(comments);
//    }

}
