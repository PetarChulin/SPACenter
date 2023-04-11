package com.example.spacenter.service;

import com.example.spacenter.model.AppUserDetails;
import com.example.spacenter.model.dto.CommentsDTO;
import com.example.spacenter.model.entity.BaseProcedure;
import com.example.spacenter.model.entity.Comment;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import com.example.spacenter.model.entity.UserEntity;
import com.example.spacenter.repositories.CommentsRepository;
import com.example.spacenter.repositories.UserRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CommentServiceTest {


    @InjectMocks
    CommentService commentService;
    @Mock
    SecurityContext securityContext;
    @Mock
    CommentsRepository commentsRepository;

    @Mock
    UserRepository userRepository;

    @Test
    void testCommentAdd() {
        // Arrange
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setContent("Test comment");

        String procedureName = "Test Procedure";
        SapropelProcedure procedure = new SapropelProcedure();

        Authentication authentication = Mockito.mock(Authentication.class);
        AppUserDetails userDetails = mock(AppUserDetails.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.setContext(securityContext);


        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getId()).thenReturn(1L);

        UserEntity user = new UserEntity();
        user.setId(1L);
        when(userRepository.getUsersById(anyLong())).thenReturn(user);

        Comment comment = new Comment();
        when(commentsRepository.save(any())).thenReturn(comment);

        // Act
        commentService.commentAdd(commentsDTO, procedure, procedureName);

        // Assert
        ArgumentCaptor<Comment> commentCaptor = ArgumentCaptor.forClass(Comment.class);
        verify(commentsRepository, times(1)).save(commentCaptor.capture());
        Comment savedComment = commentCaptor.getValue();
        assertEquals("Test Procedure", savedComment.getName());
        assertEquals("Test comment", savedComment.getContent());
    }




}
