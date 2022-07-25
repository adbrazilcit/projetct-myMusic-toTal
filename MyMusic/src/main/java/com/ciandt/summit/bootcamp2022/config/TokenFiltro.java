package com.ciandt.summit.bootcamp2022.config;

import com.ciandt.summit.bootcamp2022.applications.adapters.controllers.exception.AutenticationException;
import com.ciandt.summit.bootcamp2022.applications.adapters.controllers.exception.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class TokenFiltro extends BasicAuthenticationFilter {

    public TokenFiltro(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpClient client = HttpClient.newHttpClient();

        UserData userData = new UserData(request.getHeader("usuario"), request.getHeader("Authorization"));

        User user = new User(userData);

        HttpRequest request1 = HttpRequest.newBuilder(
                        URI.create("http://localhost:8081/api/v1/token/authorizer"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(convertObjectToJson(user)))
                .build();

        try {
            System.out.println(client.send(request1, HttpResponse.BodyHandlers.ofString()).statusCode());
            if (client.send(request1, HttpResponse.BodyHandlers.ofString()).statusCode() != 201) {
                throw new AutenticationException("Usuário não possui acesso");
            }
            chain.doFilter(request, response);
        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setStatus(HttpStatus.FORBIDDEN.value());
            errorResponse.setMessage("Autenticação inválida");
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(convertObjectToJson(errorResponse));
            response.setStatus(HttpStatus.FORBIDDEN.value());

        }

    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
