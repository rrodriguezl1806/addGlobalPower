package com.example.addGlobalPower.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by laura.estrada on 6/12/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserUpdate {

    private Long id;
    private String oldPassword;
    private String newPassword;

    public UserUpdate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
