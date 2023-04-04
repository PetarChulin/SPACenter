package com.example.spacenter.model.entity;

import com.example.spacenter.model.entity.MedicalProcedures.LaserProcedure;
import com.example.spacenter.model.entity.MedicalProcedures.SapropelProcedure;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private UserEntity author;

    @Column(name = "created_date")
    private String createdOn;

    @ManyToOne
    private SapropelProcedure sapropel;

    @ManyToOne
    private LaserProcedure laser;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }


    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public SapropelProcedure getSapropel() {
        return sapropel;
    }

    public void setSapropel(SapropelProcedure sapropel) {
        this.sapropel = sapropel;
    }

    public LaserProcedure getLaser() {
        return laser;
    }

    public void setLaser(LaserProcedure laser) {
        this.laser = laser;
    }
}
