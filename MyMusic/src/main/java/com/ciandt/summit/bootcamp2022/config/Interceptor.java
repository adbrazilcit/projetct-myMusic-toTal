package com.ciandt.summit.bootcamp2022.config;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.logging.Logger;


@Component
public class Interceptor implements HandlerInterceptor {

    private static final Logger log = Logger.getLogger(Interceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // create a client
//        HttpClient client = HttpClient.newHttpClient();
//
//        // create a request
//        HttpRequest request1 = HttpRequest.newBuilder(
//                        URI.create("http://localhost:8081/api/v1/token/authorizer"))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofFile(Path.of("./src/main/resources/data/token.json")))
//                .build();
//
//        log.info("Request: " + request1.toString());
//
//        // send the request
//        client.send(request1, HttpResponse.BodyHandlers.ofString());

        log.info("Requisição interceptada: " + request.getHeader("Authorization"));
        return true;
    }

}
