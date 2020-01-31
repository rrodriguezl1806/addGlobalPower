package com.example.addGlobalPower.dto;

import java.util.Date;

public class ProductDto {
    private long id;
    private String name;
    private String category;
    private Integer price;
    private String description;
    private Integer likes;
    private Integer noLikes;
    private Integer sold;
    private String photo;
    private Date created_date;

    public ProductDto() {
    }

    public ProductDto(long id, String name, String category, Integer price, String description, Integer likes, Integer noLikes, Integer sold, String photo, Date created_date) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.likes = likes;
        this.noLikes = noLikes;
        this.sold = sold;
        this.photo = photo;
        this.created_date = created_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getNoLikes() {
        return noLikes;
    }

    public void setNoLikes(Integer noLikes) {
        this.noLikes = noLikes;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}
