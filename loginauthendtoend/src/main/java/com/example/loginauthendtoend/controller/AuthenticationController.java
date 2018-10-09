package com.example.loginauthendtoend.controller;

import com.example.loginauthendtoend.config.JwtAuthenticationResponse;
import com.example.loginauthendtoend.config.TokenProvider;
import com.example.loginauthendtoend.model.CxxxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private TokenProvider jwtTokenUtil;

        @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
        public ResponseEntity register(@RequestBody CxxxUser cxxxUser) throws AuthenticationException {

            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            cxxxUser.getName(),
                            cxxxUser.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        }

}
