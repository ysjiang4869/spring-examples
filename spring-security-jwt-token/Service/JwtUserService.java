package com.uvlab.cloud.site.security.Service;

import com.uvlab.cloud.site.init.JxAppFoundation;
import com.uvlab.cloud.site.security.common.JwtUser;
import com.uvlab.cloud.site.service.user.JxUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jys on 2016/7/6 0006.
 */
public class JwtUserService {
    public JwtUserService() {

    }

    private  String url= JxAppFoundation.UserURL;
    //当需要同时支持手机号或邮箱登录时候，这边正则匹配name确定根据什么属性去查找
    public JwtUser getUserByname(String name) {
        JwtUser ret =new JwtUser();
        RestTemplate restTemplate=new RestTemplate();
        JxUser user= restTemplate.getForObject(url+"/user/authority?username="+name,JxUser.class);
        ret.setUsername(name);
        ret.setPassword(user.getPassword());
        ret.setEnabled(user.isEnabled());
        List<String> roles=user.getRoles();
        ArrayList<SimpleGrantedAuthority>gas=new ArrayList<>();
        for(String role: roles) {
            SimpleGrantedAuthority ga = new SimpleGrantedAuthority(role);
            gas.add(ga);
        }
        ret.setAuthorities(gas);
        return ret;
    }
}
