package com.ciandt.summit.bootcamp2022.config;

import com.ciandt.summit.bootcamp2022.applications.adapters.controllers.exception.AutenticationException;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class TokenFiltro  extends BasicAuthenticationFilter {

    public TokenFiltro(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(request.getHeader("Authorization"));
         //create a client
        HttpClient client = HttpClient.newHttpClient();

        // create a request
        HttpRequest request1 = HttpRequest.newBuilder(
                        URI.create("http://localhost:8081/api/v1/token/authorizer"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Path.of("./src/main/resources/data/token.json")))
                .build();



        try {
            System.out.println(client.send(request1, HttpResponse.BodyHandlers.ofString()).statusCode());
            if(client.send(request1, HttpResponse.BodyHandlers.ofString()).statusCode()==500) {
                System.out.println("500");
                throw new AutenticationException("Usuário não possui acesso");

            }
            System.out.println("ok");

            chain.doFilter(request, response);
        } catch (Exception e) {
            response.getWriter().write("Usuário não possui acesso");
            response.setStatus(HttpStatus.FORBIDDEN.value());

        }




//        return;

////        throw new NotFoundException("Realize a autenticação");
//        try {
////            if(client.send(request1, HttpResponse.BodyHandlers.ofString()).statusCode()==500)
////                throw new AutenticationException("Usuário não possui acesso");
////            chain.doFilter(request, response);
//        } catch (Exception e) {
//            response.getWriter().write("Usuário não possui acesso");
//            response.setStatus(HttpStatus.FORBIDDEN.value());
//
//        }

//        try {
//            System.out.println( client.send(request1, HttpResponse.BodyHandlers.ofString()).statusCode());
//            if(client.send(request1, HttpResponse.BodyHandlers.ofString()).statusCode()==500)
//                throw new AutenticationException("Realize a autenticação");
//        } catch (InterruptedException e) {
//            throw new AutenticationException(e.getMessage());
//        }


    }
}
