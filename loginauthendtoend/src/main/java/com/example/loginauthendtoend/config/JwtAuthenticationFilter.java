/*
JWT Authentication Mechanism

Following class extends OncePerRequestFilter that ensures a single execution per request dispatch.
This class checks for the authorization header and authenticates the JWT token and sets the authentication in the context.
Doing so will protect our APIs from those requests which do not have any authorization token.
The configuration about which resource to be protected and which not can be configured in
WebSecurityConfig.java
*/
package com.example.loginauthendtoend.config;

import com.example.loginauthendtoend.service.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.example.loginauthendtoend.service.SecurityConstants.HEADER_STRING;
import static com.example.loginauthendtoend.service.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        // header = Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTEiLCJzY29wZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sIm
        //lzcyI6Imh0dHA6Ly9kZXZnbGFuLmNvbSIsImlhdCI6MTUzOTM1NzA5OCwiZXhwIjoxNTM5Mzc1MDk4fQ.UaH4ah9hOkwNq6zFedU__2bPak3n7XDEOJErPVJZ6IA

        logger.info("this is the header of the token: "+header);
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            // Get only the token by removing header "Bearer"
            authToken = header.replace(TOKEN_PREFIX,"");
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }

        logger.info("SecurityContextHolder.getContext() "+SecurityContextHolder.getContext());
        logger.info("ecurityContextHolder.getContext().getAuthentication() "+SecurityContextHolder.getContext().getAuthentication());

        // To get the current logged-in Username in Spring Security: SecurityContextHolder.getContext().getAuthentication()
        //  Check is user name is null and current user not authenticated by the Spring Security
        // (need to confirm this part again, found out that this if statement is always true,
        // SecurityContextHolder.getContext().getAuthentication() is always null even after using the same account)
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // It is not compelling necessary to load the use details from the database. You could also store the information
            // in the token and read it from it. It's up to you ;)
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {


                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

                logger.info("This is the authentication of UsernamePasswordAuthenticationToken Class: "+authentication);
               //Implementation of {@link AuthenticationDetailsSource} which builds the details object
                // from an <tt>HttpServletRequest</tt> object, creating a {@code WebAuthenticationDetails}
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Servlet Filters are an implementation of the Chain of responsibility design pattern.
        // All filters are chained (in the order of their definition in web.xml).
        // The chain.doFilter() is proceeding to the net element in the chain. The last element of the chain
        // is the target resource/servet
        chain.doFilter(req, res);
    }
}