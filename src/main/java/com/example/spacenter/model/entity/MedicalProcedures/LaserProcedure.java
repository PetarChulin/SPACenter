package com.example.spacenter.model.entity.MedicalProcedures;

import com.example.spacenter.model.entity.BaseProcedure;
import com.example.spacenter.model.entity.UserEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "laser-procedures")
public class LaserProcedure extends BaseProcedure {

    @Column
    private String type = "laser";

    public LaserProcedure() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
