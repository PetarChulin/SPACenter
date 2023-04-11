package com.example.spacenter.repositories;

import com.example.spacenter.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

        List<Comment> findCommentsByName(String name);
}
