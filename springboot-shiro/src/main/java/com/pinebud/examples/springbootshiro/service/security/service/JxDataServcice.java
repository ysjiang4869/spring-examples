package com.pinebud.examples.springbootshiro.service.security.service;

import com.pinebud.examples.springbootshiro.service.security.common.JxRole;
import com.pinebud.examples.springbootshiro.service.security.common.JxUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jiangyuesong on 2016/12/26 0026.
 *
 */
public class JxDataServcice {
    private JdbcTemplate jdbcTemplate;
    private static final Logger log= LoggerFactory.getLogger(JxDataServcice.class);
    public void init(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);

    }

    public JxUser getUserByName(String username){
        JxUser ret=null;
        try {
            ret=jdbcTemplate.queryForObject("SELECT * FROM users where username= ?", new Object[]{username}, new UserMapper());
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ret;
    }

    public JxRole getRole(String rolename){
       JxRole ret=null;
        try {
            ret=jdbcTemplate.queryForObject("SELECT * FROM roles where rolename= ?", new Object[]{rolename},new RoleMapper());
        }catch (Exception e){
            log.error(e.getMessage(),e);
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
            Collections.addAll(tmp, (String[]) roles.getArray());
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
            Collections.addAll(tmp, (String[]) permissions.getArray());
            role.setPermissions(tmp);
            return role;
        }
    }

}
