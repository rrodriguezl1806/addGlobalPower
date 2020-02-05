package com.example.addGlobalPower.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @Size(min = 1, max = 10, message = "The first name must be between 1 and 10 characters")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(min = 1, max = 20, message = "The first name must be between 1 and 10 characters")
    private String lastName;

    @NotNull
    @Email
    @Column(unique = true, name = "email")
    private String email;

    private String phone;

    private String address;

    @OneToMany(mappedBy = "user")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProductUser> productUser;

    public User() {
    }

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<ProductUser> getProductUser() {
        return productUser;
    }

    public void setProductUser(List<ProductUser> productUser) {
        this.productUser = productUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
