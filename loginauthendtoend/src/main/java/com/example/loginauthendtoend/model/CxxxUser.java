package com.example.loginauthendtoend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alluser")
public class CxxxUser {

    @Id
    String id;
    String name;
    String password;
    String phone;
    String email;
    String organization;

    public CxxxUser(){

    }

    public CxxxUser(String name, String password, String phone, String email, String organization){
        this.name=name;
        this.password=password;
        this.phone=phone;
        this.email=email;
        this.organization=organization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
