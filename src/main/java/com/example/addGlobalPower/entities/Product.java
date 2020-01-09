package com.example.addGlobalPower.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String category;
    private Integer price;
    private String description;
    private Integer quantityAvailable;
    private Integer quantityBought;
    private Integer likes;
    private Integer assessment;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public Integer getQuantityBought() {
        return quantityBought;
    }

    public Integer getLikes() {
        return likes;
    }

    public Integer getAssessment() {
        return assessment;
    }

}
