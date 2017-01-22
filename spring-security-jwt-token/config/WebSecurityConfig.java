package com.uvlab.cloud.site.security.config;

import com.uvlab.cloud.site.security.JwtAuthenticationEntryPoint;
import com.uvlab.cloud.site.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService);
//                .passwordEncoder(passwordEncoder());
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // we can also do this in JxApplication but it's obviously here is better
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and() //modified default exception process method but this diabled fromlogin
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()  //because we use spring session,so it should be always!!
//               .formLogin().loginPage("/module/login/login-v1.html").and()
//                .formLogin().and()
                .headers().frameOptions().sameOrigin().and()
                // region we can control our web pages between this
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // allow anonymous resource requests
                .antMatchers(HttpMethod.GET, "/rest/file/**").permitAll()
                .antMatchers(HttpMethod.POST, "/rest/**/picture").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/faq/**").permitAll()
                .antMatchers(HttpMethod.POST, "/rest/faq/**").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/news/**").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/wiki/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/rest/user").hasRole("USER")
 //               .antMatchers(HttpMethod.GET,"/rest/user/{id}").hasRole("USER")
                .antMatchers("/rest/**").hasRole("USER")     ///TODO to open authority
                .antMatchers("/anon/**").permitAll()
                .antMatchers("/**").permitAll()         // later version needed
//                .antMatchers("/module/**").hasRole("USER")   //template version
//                .antMatchers("/**").permitAll()
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js"
//                ).permitAll()
                .antMatchers("/token/**").permitAll()
                .anyRequest().authenticated().and().requestCache().requestCache(new NullRequestCache());
        //endregion

        // Custom JWT based security filter
        httpSecurity
                .addFilter(authenticationTokenFilterBean());
        // disable page caching
        httpSecurity.headers().cacheControl();
        httpSecurity.requestCache().requestCache(new NullRequestCache());
        httpSecurity.formLogin().failureHandler(authenticationFailureHandler());
        httpSecurity.rememberMe();
    }

    private AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler();
    }

    public class AuthenticationFailureHandler
            extends SimpleUrlAuthenticationFailureHandler {
    }
}