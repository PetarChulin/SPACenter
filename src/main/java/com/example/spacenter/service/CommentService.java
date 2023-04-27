package com.example.spacenter.service;

import com.example.spacenter.model.dto.CommentsDTO;
import com.example.spacenter.model.entity.BaseProcedure;
import com.example.spacenter.model.entity.Comment;
import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.SpaProcedures.SpaRituals;
import com.example.spacenter.model.entity.SpaProcedures.SpaServices;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.CommentsRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.LaserRepository;
import com.example.spacenter.repositories.MedicalSubProceduresRepos.SapropelRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaRitualsRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static com.example.spacenter.service.CommonService.*;

@Service
public class CommentService {

    private final CommentsRepository commentsRepository;
    private final SapropelRepository sapropelRepository;

    private final LaserRepository laserRepository;

    private final SpaRitualsRepository spaRitualsRepository;

    private final SpaServicesRepository spaServicesRepository;


    public CommentService(CommentsRepository commentsRepository,
                          SapropelRepository sapropelRepository,
                          LaserRepository laserRepository,
                          SpaRitualsRepository spaRitualsRepository,
                          SpaServicesRepository spaServicesRepository) {
        this.commentsRepository = commentsRepository;
        this.sapropelRepository = sapropelRepository;
        this.laserRepository = laserRepository;
        this.spaRitualsRepository = spaRitualsRepository;
        this.spaServicesRepository = spaServicesRepository;
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
    public void createSpaRitualComment(CommentsDTO commentsDTO, Long id) {

        SpaRituals procedure = this.spaRitualsRepository.getById(id);
        String procedureName = procedure.getName();
        commentAdd(commentsDTO, procedure, procedureName);

    }

    @Transactional
    public void createSpaServiceComment(CommentsDTO commentsDTO, Long id) {

        SpaServices procedure = this.spaServicesRepository.getById(id);
        String procedureName = procedure.getName();
        commentAdd(commentsDTO, procedure, procedureName);

    }

    @Transactional
    public void deleteComment(Long id) {

        this.commentsRepository.deleteById(id);
    }

//    public boolean isOwner(Long id) {
//
//        Comment comment = this.commentsRepository.findById(id).get();
//
//        Long userId = getUserId();
//
//        return Objects.equals(comment.getAuthor().getId(), userId);
//    }

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

    public List<Comment> showCommentsByProcedureId(Long id) {

        List<BaseProcedure> all = getAllProcedures();

        BaseProcedure procedure = all.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findAny().orElseThrow();

        String name = procedure.getName();
        return commentsRepository.findCommentsByName(name);

    }

    public String findNameById(Long id) {
        List<BaseProcedure> all = getAllProcedures();

        BaseProcedure procedure = all.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findAny().orElseThrow();

        return procedure.getName();
    }
}
