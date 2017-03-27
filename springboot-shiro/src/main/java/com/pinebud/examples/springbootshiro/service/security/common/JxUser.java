package com.pinebud.examples.springbootshiro.service.security.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangyuesong on 2016/12/26 0026.
 *
 */
public class JxUser {
    private int id;
    private String username;
    private String password;
    private List<String> roles=new ArrayList<>();


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
