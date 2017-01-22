package com.uvlab.cloud.site.security;

import com.hazelcast.core.HazelcastInstance;
import com.uvlab.cloud.site.security.Service.JwtTokenService;
import com.uvlab.cloud.site.service.JxAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenService tokensvc;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Bean
    public  SessionRepository<ExpiringSession> sessionRepository(){
        return  new MapSessionRepository(hazelcastInstance.getMap("spring:session:sessions"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        //String authToken = httpRequest.getHeader("Token");
        HttpSession session=httpRequest.getSession();
        // authToken.startsWith("Bearer ")
        // String authToken = header.substring(7);
      //  String username = tokensvc.getUsernameFromToken(authToken);

       /* if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);  //get user information from database everytime
            if (tokensvc.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }*/
        //上述为旧的纯token的验证方式，现在是新的验证方式，将token放入session中，前端通过添加x-auth-token的头部，从session中直接获取
        //SecurityContextHolder.getContext().getAuthentication()对于新的session或者通过session controller都已经进行了赋值
        //必须对新的session进行if内的处理才能保证request的x-auth-token和session对应而不会新建session，猜测是setAuthentication与spring security的作用
        if(session.isNew()&&SecurityContextHolder.getContext().getAuthentication() == null){
             ArrayList<SimpleGrantedAuthority> roles=(ArrayList)session.getAttribute("roles");
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(null, null,roles);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //设置新session有效时间，还需要设置redis的有效时间，刷新策略未知，可以利用已有的session新建一个session替换解决刷新和redis有效时间的问题
            //hazelcast下不需要自己设置以及以下设置无效
            //session.setMaxInactiveInterval(120);
        }
        chain.doFilter(request, response);
    }

//    @Override
//    protected  boolean preHandle(ServletRequest request, ServletResponse response){
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse=(HttpServletResponse)response;
//        if(httpRequest.getMethod().equals(RequestMethod.OPTIONS.name()))
//        {
//            httpResponse.setStatus(HttpStatus.SC_OK);
//            return false;
//        }
//        return super.preHandle(request,response);
//    }
}