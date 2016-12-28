package com.pinebud.examples.springbootshiro.service.security.common;

import java.util.List;

/**
 * Created by jiangyuesong on 2016/12/26 0026.
 */
public class JxUser {
    int id;
    String username;
    String password;
    List<String> roles;

    public JxUser() {
    }

    public JxUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
