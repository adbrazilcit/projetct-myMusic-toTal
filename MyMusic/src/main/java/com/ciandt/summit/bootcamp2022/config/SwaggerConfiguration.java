package com.ciandt.summit.bootcamp2022.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
//@EnableSwagger2
public class SwaggerConfiguration  extends WebMvcConfigurationSupport {

//    @Bean
//    public Docket docket() {
//        return  new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }

}
