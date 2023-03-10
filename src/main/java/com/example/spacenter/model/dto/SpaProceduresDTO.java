package com.example.spacenter.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SpaProceduresDTO {

    @NotNull
    @Size(min = 5 , message = "Name must be at least 5 characters long")
    private String name;

    @NotEmpty(message = "Can not be empty")
    private String imageUrl;

    @NotNull
    @Size(min = 5 , message = "Description must be at least 5 characters long")
    private String description;

    public SpaProceduresDTO() {
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
}
