package com.pinebud.examples.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
@SpringBootApplication
public class JxApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Object[]{JxApplication.class}, args);

    }
}
