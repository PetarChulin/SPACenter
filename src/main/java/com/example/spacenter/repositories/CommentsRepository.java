package com.example.spacenter.repositories;

import com.example.spacenter.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comment, Long> {


}
