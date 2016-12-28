package com.pinebud.examples.springbootshiro.service.security.service;

import com.pinebud.examples.springbootshiro.service.security.common.JxRole;
import com.pinebud.examples.springbootshiro.service.security.common.JxUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangyuesong on 2016/12/26 0026.
 */
public class JxDataServcice {
    private JdbcTemplate jdbcTemplate;

    public void init(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);

    }

    public JxUser getUserByName(String username){
        JxUser ret=null;
        try {
            ret=jdbcTemplate.queryForObject("SELECT * FROM users where username= ?", new Object[]{username}, new UserMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    public JxRole getRole(String rolename){
       JxRole ret=null;
        try {
            ret=jdbcTemplate.queryForObject("SELECT * FROM roles where rolename= ?", new Object[]{rolename},new RoleMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }


    private static final class UserMapper implements RowMapper<JxUser> {
        public JxUser mapRow(ResultSet rs, int rowNUM) throws SQLException {
            JxUser user = new JxUser();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            Array roles = rs.getArray("roles");
            List<String> tmp=new ArrayList<>();
            for (String role : (String[]) roles.getArray()) {
                tmp.add(role);
            }
            user.setRoles(tmp);
            return user;
        }
    }

    private static final class RoleMapper implements RowMapper<JxRole> {
        public JxRole mapRow(ResultSet rs,int rowNUM) throws SQLException{
            JxRole role=new JxRole();
            role.setId(rs.getInt("id"));
            role.setRolename(rs.getString("rolename"));
            Array permissions=rs.getArray("permission");
            List<String> tmp=new ArrayList<>();
            for(String permission: (String[])permissions.getArray()) {
                tmp.add(permission);
            }
            role.setPermissions(tmp);
            return role;
        }
    }

}
