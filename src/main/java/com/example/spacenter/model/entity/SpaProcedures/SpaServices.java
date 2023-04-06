package com.example.spacenter.model.entity.SpaProcedures;

import com.example.spacenter.model.entity.BaseProcedure;
import jakarta.persistence.*;

@Entity
@Table(name = "spa_services")
public class SpaServices extends BaseProcedure {


    @Column
    private String type = "spa-services";

    public SpaServices() {
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}
