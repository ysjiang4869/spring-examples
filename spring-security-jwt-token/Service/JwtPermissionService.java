package com.uvlab.cloud.site.security.Service;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by YueSong on 2017/1/14.
 */
@Component
public class JwtPermissionService implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomain, Object permission) {
        System.out.println(targetDomain.toString());
        if(targetDomain.toString().equals("test")) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
