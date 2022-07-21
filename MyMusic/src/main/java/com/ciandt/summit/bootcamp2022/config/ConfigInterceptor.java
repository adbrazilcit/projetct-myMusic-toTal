package com.ciandt.summit.bootcamp2022.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class ConfigInterceptor implements WebMvcConfigurer {

    private  final  Interceptor inperceptor;

    public ConfigInterceptor(Interceptor inperceptor) {
        this.inperceptor = inperceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(inperceptor).addPathPatterns("/**");
    }

}
