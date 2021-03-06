package com.epam.demo.managerassignment.model.restmodel;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductResponseModel implements Serializable {
    private String title;
    private String description;
    private Double price;

    public ProductResponseModel(String title, String description, Double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public ProductResponseModel() {
    }
}
