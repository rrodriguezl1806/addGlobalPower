package com.example.addGlobalPower.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 1, max = 20, message = "The product's name must be between 1 and 10 characters")
    private String name;

    @NotNull
    private String category;

    @NotNull
    private Integer price;

    @Size(max = 100)
    private String description;

    @OneToMany(mappedBy = "product")
    private List<ProductUser> productUser;

    private Integer likes = 0;

    private Integer noLikes = 0;

    private Integer sold = 0;

    private String photo;

    public Product() {
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

    public List<ProductUser> getProductUser() {
        return productUser;
    }

    public void setProductUser(List<ProductUser> productUser) {
		this.productUser = productUser;
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

}

	