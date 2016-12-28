package com.pinebud.examples.springbootshiro.service.security.common;

import java.util.List;

/**
 * Created by jiangyuesong on 2016/12/26 0026.
 */
public class JxRole {
    int id;
    String rolename;
    List<String> permissions;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
