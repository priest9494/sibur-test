package com.example.siburtest.repositories;

import com.example.siburtest.domains.Customer;
import com.example.siburtest.exceptions.CustomerException;

public interface CustomerAuthRepository {
    Integer create(String username, String password) throws CustomerException;
    Customer findByUsernameAndPassword(String username, String password) throws CustomerException;
    Integer getCountByUsername(String username);

    Customer findById(Integer userId);
}
