package com.example.loginauthendtoend.service;
//
//import com.example.loginauthendtoend.model.CxxxUser;
//import com.example.loginauthendtoend.repository.CxxxUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////
/////* Creating an Authentication Service
////- We need to tell Spring where our user data is located
////& where to find the information it needs for authentication.
////- To do this, we can create an Authentication Service.
//// */
////
////
////@Service
////public class CustomUserDetailsService implements UserDetailsService {
////
////    @Autowired
////    private CxxxUserRepository cxxxRepository;
////    // Method for getting user by name
////    public CxxxUser findCxxxUserByName(String name) {
////        return cxxxRepository.findCxxxUserByName(name);
////    }
////
////
////    // Method for handling the login mechanism that checks or
////    // compares username with the user from MongoDB collection.
////    @Override
////    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
////
////        CxxxUser user = cxxxRepository.findCxxxUserByName(name);
////        if(user != null) {
////            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
////            return buildUserForAuthentication(user, authorities);
////        } else {
////            throw new UsernameNotFoundException("username not found");
////        }
////    }
////
////}
//
import com.example.loginauthendtoend.model.CxxxUser;
import com.example.loginauthendtoend.repository.CxxxUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    CxxxUserRepository cxxxUserRepository;

    // Hard coded username, password and role without data base
    // Enhance it after experimenting
    // Everything should be from database
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CxxxUser cxxxUser=loadApplicationUserByUsername(username);
        return new User(cxxxUser.getName(),cxxxUser.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

    public CxxxUser loadApplicationUserByUsername(String name){
        // Enhanced Line: To find username in database
        CxxxUser c = cxxxUserRepository.findCxxxUserByName(name);
        // Original Code from YT:
        // return new CxxxUser("batman","{noop}pass");
        return c;
    }

    //    public boolean login(@RequestBody CxxxUser cxxxuser) {
//
//        CxxxUser c = cxxxUserRepository.findCxxxUserByName(cxxxuser.getName());
//
//        if (c != null) {
//            return cxxxuser.getName().equals(c.getName()) && cxxxuser.getPassword().equals(c.getPassword());
//        }
//        return false;
//    }
}
