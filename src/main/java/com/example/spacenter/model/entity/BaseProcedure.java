package com.example.spacenter.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@MappedSuperclass
public abstract class BaseProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Double price;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserEntity> buyers;

    public void addBuyer(UserEntity user) {

        this.buyers.add(user);
    }

    public void removeBuyer(UserEntity user) {
        this.buyers.remove(user);
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
}
