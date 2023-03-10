package com.example.spacenter.model.entity.MedicalProcedures;

import com.example.spacenter.model.entity.BaseProcedure;
import com.example.spacenter.model.entity.UserEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "laser-procedures")
public class LaserProcedure extends BaseProcedure {

    @Column
    private Double price;

    @Column
    private String type = "laser";

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserEntity> buyers;


    public void addBuyer(UserEntity user) {

        this.buyers.add(user);
    }

    public void removeBuyer(UserEntity user) {
        this.buyers.remove(user);
    }


    public LaserProcedure() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<UserEntity> getBuyers() {
        return buyers;
    }

    public void setBuyers(Set<UserEntity> buyers) {
        this.buyers = buyers;
    }

}
