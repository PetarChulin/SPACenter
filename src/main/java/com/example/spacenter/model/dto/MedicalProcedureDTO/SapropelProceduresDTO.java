package com.example.spacenter.model.dto.MedicalProcedureDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class SapropelProceduresDTO {

    @NotNull
    @Size(min = 5 , message = "Name must be at least 5 characters long")
    private String name;

    @NotEmpty(message = "Can not be empty")
    private String imageUrl;

    @NotNull
    @Positive(message = "Price must be positive")
    private Double price;

    @NotNull
    private String description;

    public SapropelProceduresDTO() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
