package com.example.spacenter.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@MappedSuperclass
public abstract class BaseProcedure implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Double price;

    @Column
    private String type;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserEntity> buyers;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<Comment> comments;

    public void addBuyer(UserEntity user) {

        this.buyers.add(user);
    }

    public void removeBuyer(UserEntity user) {
        this.buyers.remove(user);
    }

    public void addComment(Comment comment) {

        this.comments.add(comment);
    }
    public void deleteComment(Comment comment) {

        this.comments.remove(comment);
    }


    public BaseProcedure() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<UserEntity> getBuyers() {
        return buyers;
    }

    public void setBuyers(Set<UserEntity> buyers) {
        this.buyers = buyers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
