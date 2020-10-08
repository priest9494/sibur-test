package com.example.siburtest.controllers;

import com.example.siburtest.domains.Order;
import com.example.siburtest.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("{customerId}/orders")
    public List<Order> getOrders(@PathVariable Integer customerId) {
        return customerService.getOrders(customerId);
    }

    @GetMapping("{customerId}/orders/{orderId}")
    public Order getOrder(@PathVariable Integer customerId, @PathVariable Integer orderId) {
        return customerService.getOrder(customerId, orderId);
    }

    @PostMapping("{customerId}/orders")
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody Map<String, Object> orderMap, @PathVariable Integer customerId) {
        Integer transportTypeId = (Integer) orderMap.get("transportTypeId");
        String fullName = (String) orderMap.get("fullName");
        String phone = (String) orderMap.get("phoneNumber");
        customerService.createOrder(customerId, transportTypeId, fullName, phone);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Order created successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
