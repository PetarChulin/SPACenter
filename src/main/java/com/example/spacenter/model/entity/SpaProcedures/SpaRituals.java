package com.example.spacenter.model.entity.SpaProcedures;

import com.example.spacenter.model.entity.BaseProcedure;
import jakarta.persistence.*;


@Entity
@Table(name = "spa_rituals")
public class SpaRituals extends BaseProcedure {

    @Column
    private String type = "spa-rituals";

    public SpaRituals() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
