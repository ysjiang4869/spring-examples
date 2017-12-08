package org.jys.server.site.foundation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Administrator on 2016/6/20 0020.
 *
 */
@Configuration
@EnableSwagger2
public class JxSwaggerConfig {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).host("euvee.vicp.co")
                .securityContexts(newArrayList(securityContext())).securitySchemes(newArrayList(apiKey()))
                .useDefaultResponseMessages(false)
                .protocols(protocols())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.jys.server.site"))
                .paths(PathSelectors.any())
                .build();
    }
    private Set<String> protocols(){
        LinkedHashSet<String> ret=new LinkedHashSet<>();
        ret.add("HTTP");
        ret.add("HTTPS");
        return ret;
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("UVLAB.SITE")
                .description("Euvee Medical Technology Private Rest API")
                .contact(new Contact("EUVEE", "https://euvee.vicp.co", "jiangYS@euvee.com"))
                .termsOfServiceUrl("https://euvee.vicp.co")
                .version("1.0")
                .license("Apache License Version 2.0")
                .build();
    }

    @Bean
    SecurityScheme apiKey() {
        return new ApiKey("x-auth-token", "x-auth-token", "header");
    }

    //设定了不同的rest路径与对应的auth的匹配
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("x-auth-token", authorizationScopes));
    }
}

