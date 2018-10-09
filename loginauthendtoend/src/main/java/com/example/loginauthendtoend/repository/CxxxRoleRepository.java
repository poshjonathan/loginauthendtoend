package com.example.loginauthendtoend.repository;

import com.example.loginauthendtoend.model.CxxxRole;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CxxxRoleRepository extends MongoRepository<CxxxRole, String> {

    CxxxRole findByRole(String role);
}
