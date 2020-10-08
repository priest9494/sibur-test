package com.example.siburtest.controllers;

import com.example.siburtest.domains.Order;
import com.example.siburtest.services.DispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dispatchers")
public class DispatcherController {

    @Autowired
    DispatcherService dispatcherService;

    @GetMapping("{dispatcherId}/orders")
    public List<Order> getOrders(@PathVariable Integer dispatcherId) {
        return dispatcherService.getOrders();
    }

    @PatchMapping("{dispatcherId}/orders")
    public ResponseEntity<Map<String, String>> getOrders(@PathVariable Integer dispatcherId, @RequestBody Map<String, Object> orderMap) {
        Integer orderId = (Integer) orderMap.get("orderId");
        String status = (String) orderMap.get("status");
        dispatcherService.changeStatus(orderId, status);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Status changed successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
