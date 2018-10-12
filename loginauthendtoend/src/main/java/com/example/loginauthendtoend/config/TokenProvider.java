//package com.example.loginauthendtoend.config;
//
//import com.example.loginauthendtoend.model.CxxxUser;
//import io.jsonwebtoken.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Date;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//@Component
//public class TokenProvider {
//
//
//    public String generateToken(Authentication authentication) {
//        final String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                //.claim(AUTHORITIES_KEY, authorities) <- Original from Devglan website
//                .claim("AUTHORITIES_KEY", authorities)
//                //.signWith(SignatureAlgorithm.HS256, SIGNING_KEY) <- Original from Devglan website
//                .signWith(SignatureAlgorithm.HS256, "SIGNING_KEY")
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                //.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
//                .setExpiration(new Date(System.currentTimeMillis() + 60*60*1000))
//                .compact();
//    }
//
//    UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {
//
//        final JwtParser jwtParser = Jwts.parser().setSigningKey("SIGNING_KEY");
//
//        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
//
//        final Claims claims = claimsJws.getBody();
//
//        final Collection authorities =
//                Arrays.stream(claims.get("AUTHORITIES_KEY").toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
//    }
//
//    public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//    public Date getIssuedAtDateFromToken( String token )
//    {
//        return getClaimFromToken(token, Claims::getIssuedAt);
//    }
//    public <T> T getClaimFromToken( String token, Function<Claims, T> claimsResolver )
//    {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims getAllClaimsFromToken( String token )
//    {
//        return Jwts.parser().setSigningKey("SIGNING_KEY").parseClaimsJws(token).getBody();
//    }
//
//
//    // From:
//    // https://github.com/szerhusenBC/jwt-spring-security-demo/blob/master/src/main/java/org/zerhusen/config/WebSecurityConfig.java
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        CxxxUser cxxxUser = (CxxxUser) userDetails;
//        final String username = getUsernameFromToken(token);
//        final Date created = getIssuedAtDateFromToken(token);
//        //final Date expiration = getExpirationDateFromToken(token);
//        return (
//                username.equals(cxxxUser.getName())
//                       // && !isTokenExpired(token)
//                       // && !isCreatedBeforeLastPasswordReset(created, cxxxUser.getLastPasswordResetDate())
//        );
//    }
//
//
//}
