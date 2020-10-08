package com.example.siburtest.services;

import com.example.siburtest.domains.Customer;
import com.example.siburtest.exceptions.CustomerException;
import com.example.siburtest.repositories.CustomerAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerAuthServiceImpl implements CustomerAuthService {
    @Autowired
    CustomerAuthRepository customerAuthRepository;

    @Override
    public Customer validateCustomer(String username, String password) throws CustomerException {
        if (username != null) username = username.toLowerCase();
        return customerAuthRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Customer registerCustomer(String username, String password) throws CustomerException {
        if (username.length() < 4) throw new CustomerException("Ivalid Username");
        if (password.length() < 4) throw new CustomerException("Ivalid Password");

        Integer count = customerAuthRepository.getCountByUsername(username);
        if (count > 0) throw  new CustomerException("Username already in use");

        Integer customerId = customerAuthRepository.create(username, password);
        return customerAuthRepository.findById(customerId);
    }
}
