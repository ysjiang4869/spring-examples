package com.pinebud.examples.springboot.war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
@SpringBootApplication
public class JxApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JxApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Object[]{JxApplication.class}, args);

    }
}
