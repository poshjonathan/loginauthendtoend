//package com.example.loginauthendtoend.service;
//
//import com.example.loginauthendtoend.model.CxxxUser;
//import com.example.loginauthendtoend.repository.CxxxUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///* Creating an Authentication Service
//- We need to tell Spring where our user data is located
//& where to find the information it needs for authentication.
//- To do this, we can create an Authentication Service.
// */
//
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private CxxxUserRepository cxxxRepository;
//    // Method for getting user by name
//    public CxxxUser findCxxxUserByName(String name) {
//        return cxxxRepository.findCxxxUserByName(name);
//    }
//
//
//    // Method for handling the login mechanism that checks or
//    // compares username with the user from MongoDB collection.
//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//
//        CxxxUser user = cxxxRepository.findCxxxUserByName(name);
//        if(user != null) {
//            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
//            return buildUserForAuthentication(user, authorities);
//        } else {
//            throw new UsernameNotFoundException("username not found");
//        }
//    }
//
//}
