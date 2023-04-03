package com.example.spacenter.service;

import com.example.spacenter.model.dto.CommentsDTO;
import com.example.spacenter.model.entity.BaseProcedure;
import com.example.spacenter.model.entity.Comment;
import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.CommentsRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

import static com.example.spacenter.service.CommonService.getUserEntity;
import static com.example.spacenter.service.CommonService.getUserId;

@Service
public class CommentService {

    private final CommentsRepository commentsRepository;
    private SapropelRepository sapropelRepository;

    private LaserRepository laserRepository;


    public CommentService(CommentsRepository commentsRepository, SapropelRepository sapropelRepository, LaserRepository laserRepository) {
        this.commentsRepository = commentsRepository;
        this.sapropelRepository = sapropelRepository;
        this.laserRepository = laserRepository;
    }


    @Transactional
    public void createSapropelComment(CommentsDTO commentsDTO, Long id) {

        SapropelProcedure procedure = this.sapropelRepository.getById(id);
        String procedureName = procedure.getName();
        commentAdd(commentsDTO, procedure, procedureName);
    }

    @Transactional
    public void createLaserComment(CommentsDTO commentsDTO, Long id) {

        LaserProcedure procedure = this.laserRepository.getById(id);
        String procedureName = procedure.getName();
        commentAdd(commentsDTO, procedure, procedureName);

    }

    @Transactional
    public void deleteComment(Long id) {

//        SapropelProcedure sapropelProcedure = this.sapropelRepository.findById(id).get();
//        Comment comment = this.commentsRepository.findById(id).get();
        this.commentsRepository.deleteById(id);
    }

    public boolean isOwner(Long id) {

        Comment comment = this.commentsRepository.findById(id).get();

        Long userId = getUserId();

        return Objects.equals(comment.getAuthor().getId(), userId);
    }

    @Transactional
    void commentAdd(CommentsDTO commentsDTO, BaseProcedure procedure, String procedureName) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        UserEntity user = getUserEntity();

        Comment comment = new Comment();

        comment.setName(procedureName);
        comment.setContent(commentsDTO.getContent());
        comment.setAuthor(user);
        comment.setCreatedOn(dtf.format(now));
//        switch (procedure.getType()) {
//            case "sapropel" -> {
//                comment.setSapropel((SapropelProcedure) procedure);
//                procedure.addComment(comment);
//            }
//            case "laser" -> {
//                comment.setLaser((LaserProcedure) procedure);
//                procedure.addComment(comment);
//            }
//        }


        this.commentsRepository.save(comment);
    }

}
