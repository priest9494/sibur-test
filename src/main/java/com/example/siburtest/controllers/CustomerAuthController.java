package com.example.siburtest.controllers;

import com.example.siburtest.domains.Customer;
import com.example.siburtest.services.CustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerAuthController {

    @Autowired
    CustomerAuthService customerAuthService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerCustomer(@RequestBody Map<String, Object> customerMap) {
        String username = (String) customerMap.get("username");
        String password = (String) customerMap.get("password");

        Customer customer = customerAuthService.registerCustomer(username, password);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Registered successfully");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, Object> customerMap) {
        String username = (String) customerMap.get("username");
        String password = (String) customerMap.get("password");

        Customer customer = customerAuthService.validateCustomer(username, password);
        Map<String, String> map = new HashMap<>();
        map.put("message", "LoggedIn successfully");
        map.put("id", Integer.toString(customer.getId()));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
