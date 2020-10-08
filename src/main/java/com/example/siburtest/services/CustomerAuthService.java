package com.example.siburtest.services;

import com.example.siburtest.domains.Customer;
import com.example.siburtest.exceptions.CustomerException;

public interface CustomerAuthService {
    Customer validateCustomer(String username, String password) throws CustomerException;
    Customer registerCustomer(String username, String password) throws CustomerException;
}
