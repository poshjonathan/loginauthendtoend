//package com.example.loginauthendtoend.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Role;
//import org.springframework.data.mongodb.core.ReactiveMongoOperations;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Component
//public class JwtTokenProvider {
//
//    @Value("${security.jwt.token.secret-key}")
//    private String secretKey;
//
//    @Value("${security.jwt.token.expire-length}")
//    private long validityInMilliseconds; // 24h
//
//    @Autowired
//    private ReactiveMongoOperations operations;
//
//
//    // Create a JWT Token for the username and roles provided.
//    // The claims of the token are the roles of Spring Boot.
//    // The expiration is set by the validityInMilliseconds that is set
//    // in the application.properties.
//    //
//    // The key to signing the JWT Token is set by the secretKey that
//    // is also set in the application.properties and needs to be
//    // base64-encoded.
//    public String createToken(String username, List<Role> roles) {
//        Claims claims = Jwts.claims().setSubject(username);
//        claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority()))
//                .filter(Objects::nonNull).collect(Collectors.toList()));
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliseconds);
//        String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS256, encodedSecretKey).compact();
//    }
//
//    // Encode the secretKey and read the claims of the token.
//    public Optional<Jws<Claims>> getClaims(Optional<String> token) {
//        if (!token.isPresent()) {
//            return Optional.empty();
//        }
//        String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//        return Optional.of(Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token.get()));
//    }
//
//    // Create a UsernamePasswordToken based on the values of the
//    // user in the Mongo DB for the authentication in the filter chain.
//    public Authentication getAuthentication(String token) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userId").is(getUsername(token)));
//        MyUser user = operations.findOne(query, MyUser.class).block();
//        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
//    }
//
//    // Read the username out of the JWT Token.
//    public String getUsername(String token) {
//        String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//        return Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token).getBody().getSubject();
//    }
//
//    // Read the JWT Token out of the HTTP header and
//    // returns the token string.
//    public String resolveToken(HttpServletRequest req) {
//        String bearerToken = req.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7, bearerToken.length());
//        }
//        return null;
//    }
//
//    // Validate the token with the encoded secretKey.
//    // That makes sure that the token has not been tampered with.
//    public boolean validateToken(String token) {
//        String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//        try {
//            Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            throw new RuntimeException("Expired or invalid JWT token");
//        }
//    }
//
//
//
//}
