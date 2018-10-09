package com.example.loginauthendtoend.repository;

import com.example.loginauthendtoend.model.CxxxUser;
import org.springframework.data.repository.CrudRepository;

public interface CxxxUserRepository extends CrudRepository<CxxxUser, String> {

    CxxxUser findContactById(String id);
    CxxxUser findCxxxUserByName(String name);

    @Override
    void delete(CxxxUser deleted);
}
