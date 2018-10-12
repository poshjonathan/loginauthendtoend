//package com.example.loginauthendtoend.service;
//
//import com.example.loginauthendtoend.model.CxxxUser;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//
//import static com.example.loginauthendtoend.service.SecurityConstants.*;
//
//public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private AuthenticationManager authenticationManager;
//
//    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
//        this.authenticationManager=authenticationManager;
//
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//           CxxxUser cxxxUser= new ObjectMapper().readValue(request.getInputStream(), CxxxUser.class);
//           // If this is successful, it will proceed to successfulAuthentication method below
//           return authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(cxxxUser.getName(),cxxxUser.getPassword())));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        ZonedDateTime expirationTimeUTC =ZonedDateTime.now(ZoneOffset.UTC).plus(EXPIRATION_TIME, ChronoUnit.MILLIS);
//    String token= Jwts.builder().setSubject(((User)authResult.getPrincipal()).getUsername())
//            .setExpiration(Date.from(expirationTimeUTC.toInstant()))
//            .signWith(SignatureAlgorithm.HS256, SECRET)
//            .compact();
//
//    response.getWriter().write(token);
//   response.addHeader(HEADER_STRING, TOKEN_PREFIX+token);
//
//    }
//}
