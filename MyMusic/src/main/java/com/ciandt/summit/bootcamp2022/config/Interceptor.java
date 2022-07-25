package com.ciandt.summit.bootcamp2022.config;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;


@Component
public class Interceptor implements HandlerInterceptor {

    private static final Logger log = Logger.getLogger(Interceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Requisição interceptada: " + request.getHeader("Authorization"));
        return true;
    }

}
