//package com.example.loginauthendtoend.service;
//
//import com.example.loginauthendtoend.model.CxxxUser;
//import com.example.loginauthendtoend.repository.CxxxUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service(value = "userService")
//public class UserServiceImpl implements UserDetailsService, UserService {
//
//
//
//    @Autowired
//    private CxxxUserRepository cxxxUserRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder bcryptEncoder;
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        CxxxUser user = cxxxUserRepository.findCxxxUserByName(username);
//        if(user == null){
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//       return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getAuthority(user));
//    }
//
//    private Set getAuthority(CxxxUser user) {
//        Set authorities = new HashSet<>();
////        user.getRoles().forEach(role -> {
////            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
////        });
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
//        return authorities;
//    }
////
////    public List findAll() {
////        List list = new ArrayList<>();
////        userDao.findAll().iterator().forEachRemaining(list::add);
////        return list;
////    }
////
////    @Override
////    public void delete(long id) {
////        userDao.deleteById(id);
////    }
////
////    @Override
////    public User findOne(String username) {
////        return userDao.findByUsername(username);
////    }
////
////    @Override
////    public User findById(Long id) {
////        return userDao.findById(id).get();
////    }
////
////   @Override
////    public User save(UserDto user) {
////        User newUser = new User();
////        newUser.setUsername(user.getUsername());
////        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
////        newUser.setAge(user.getAge());
////        newUser.setSalary(user.getSalary());
////        return userDao.save(newUser);
////    }
//}
