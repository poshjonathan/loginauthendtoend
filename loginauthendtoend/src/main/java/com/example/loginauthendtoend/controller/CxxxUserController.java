package com.example.loginauthendtoend.controller;

import com.example.loginauthendtoend.model.CxxxUser;
import com.example.loginauthendtoend.repository.CxxxUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CxxxUserController {
    @Autowired
    CxxxUserRepository cxxxUserRepository;


    //---------------------Login a User (JWT)---------------------------------------

//    @PostMapping("/login")
//    public Mono<MyUser> postUserLogin(@RequestBody MyUser myUser,HttpServletRequest request)
//            throws NoSuchAlgorithmException, InvalidKeySpecException {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userId").is(myUser.getUserId()));
//        return this.operations.findOne(query, MyUser.class).switchIfEmpty(Mono.just(new MyUser()))
//                .map(user1 -> loginHelp(user1, myUser.getPassword()));
//    }
//    private MyUser loginHelp(MyUser user, String passwd) {
//        if (user.getUserId() != null) {
//            String encryptedPassword;
//            try {
//                encryptedPassword = this.passwordEncryption.getEncryptedPassword(passwd, user.getSalt());
//            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//                return new MyUser();
//            }
//            if (user.getPassword().equals(encryptedPassword)) {
//                String jwtToken = this.jwtTokenProvider.createToken(user.getUserId(), Arrays.asList(Role.USERS));
//                user.setToken(jwtToken);
//                user.setPassword("XXX");
//                return user;
//            }
//        }
//        return new MyUser();
//    }

    //---------------------Login a User---------------------------------------

    @RequestMapping(method=RequestMethod.POST, value= "/login")
    public boolean login(@RequestBody CxxxUser cxxxuser) {

        CxxxUser c = cxxxUserRepository.findCxxxUserByName(cxxxuser.getName());

        if (c != null) {
            return cxxxuser.getName().equals(c.getName()) && cxxxuser.getPassword().equals(c.getPassword());
        }
        return false;
    }

    //---------------------Show User's Details---------------------------------------

    @RequestMapping(method=RequestMethod.GET, value="/dashboard/{name}")
    public CxxxUser show(@PathVariable String name) {
        return cxxxUserRepository.findCxxxUserByName(name);
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }

    @RequestMapping(method= RequestMethod.GET, value="/allusers")
    public Iterable<CxxxUser> cxxxuser () {

        return cxxxUserRepository.findAll();

    }

    //---------------------Create a User---------------------------------------

    @RequestMapping(method=RequestMethod.POST, value="/allusers")
    public CxxxUser save(@RequestBody CxxxUser cxxxuser) {
        cxxxUserRepository.save(cxxxuser);
        return cxxxuser;
    }

//    @RequestMapping(method=RequestMethod.GET, value="/allusers/{id}")
//    public CxxxUser show(@PathVariable String id) {
//        return cxxxUserRepository.findContactById(id);
//    }

    @RequestMapping(method=RequestMethod.PUT, value="/allusers/{id}")
    public CxxxUser update(@PathVariable String id, @RequestBody CxxxUser cxxxuser) {
        CxxxUser c = cxxxUserRepository.findContactById(id);
        if(cxxxuser.getName() != null)
            c.setName(cxxxuser.getName());
        if(cxxxuser.getPassword() != null)
            c.setPassword(cxxxuser.getPassword());
        if(cxxxuser.getPhone() != null)
            c.setPhone(cxxxuser.getPhone());
        if(cxxxuser.getEmail() != null)
            c.setEmail(cxxxuser.getEmail());
        if(cxxxuser.getOrganization() != null)
            c.setOrganization(cxxxuser.getOrganization());
        cxxxUserRepository.save(c);
        return cxxxuser;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/allusers/{id}")
    public String delete(@PathVariable String id) {
        CxxxUser cxxxuser = cxxxUserRepository.findContactById(id);
        cxxxUserRepository.delete(cxxxuser);
        return "";
    }
}
