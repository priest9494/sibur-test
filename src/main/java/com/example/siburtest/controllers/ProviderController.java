package com.example.siburtest.controllers;

import com.example.siburtest.domains.Order;
import com.example.siburtest.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @GetMapping("{providerId}/orders")
    public List<Order> getOrders(@PathVariable Integer providerId) {
        return providerService.getOrders();
    }

    @PatchMapping("{providerId}/orders")
    public ResponseEntity<Map<String, String>> getOrders(@PathVariable Integer providerId, @RequestBody Map<String, Object> orderMap) {
        Integer orderId = (Integer) orderMap.get("orderId");

        String status = (String) orderMap.get("status");

        if (status.equals("DONE")) {
            Integer transportId = (Integer) orderMap.get("transportId");
            providerService.setTransport(orderId, transportId);
        } else {
            providerService.declineOrder(orderId);
        }
        Map<String, String> map = new HashMap<>();
        map.put("message", "Status changed successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
