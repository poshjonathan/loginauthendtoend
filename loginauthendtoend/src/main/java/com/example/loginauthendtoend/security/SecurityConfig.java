//package com.example.loginauthendtoend.security;
//
//import com.example.loginauthendtoend.config.CorsFilter;
//import com.example.loginauthendtoend.service.CustomUserDetailsService;
//import com.example.loginauthendtoend.service.JWTAuthenticationFilter;
//import com.example.loginauthendtoend.service.JWTAuthorizationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.channel.ChannelProcessingFilter;
//
//@ComponentScan
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) // To enable @PreAuthorize("hasAnyRole('ADMIN')") @ end points or controller
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CustomUserDetailsService customUserDetailsService;
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
//
//        this.customUserDetailsService = customUserDetailsService;
//    }
//
//
//
//    //Disable some features offered to us
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
//        http.cors().and().csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .antMatchers("/*/floor1/**").hasRole("USER")
//                .antMatchers("/*/floor2/**").hasRole("ADMIN")
//                .and()
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager(),customUserDetailsService));
//    }
//}
//
