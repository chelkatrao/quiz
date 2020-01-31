package com.company.quiz.security.jwt;

import com.company.quiz.dto.auth.UserDetailDto;
import com.company.quiz.service.auth.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class JWTUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private Gson gson = new Gson();

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    public JWTUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {

            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getPassword(), //  credential = password
                    authenticationRequest.getUsername()  //  principal = username
            );

            UserDetailDto dto = userService.getUserByUsername(authenticationRequest.getUsername());
            Authentication authenticate = null;
            if (dto != null) {
                authenticate = authenticationManager.authenticate(authentication);
            } else {
                Gson gson = new Gson();
                Map map = new HashMap();
                map.put("timestamp", LocalDateTime.now());
                map.put("message", "Access denied");
                gson.toJson(map);
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write(gson.toJson(map));
            }
            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String key = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";

        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());

        String toke = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(secretKey)
                .compact();

        Map responseDate = new TreeMap();

        responseDate.put("token", "Bearer " + toke);
        responseDate.put("permissions", authResult.getAuthorities());
        responseDate.put("username", authResult.getName());

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(gson.toJson(responseDate));
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
