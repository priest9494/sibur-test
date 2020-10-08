package com.example.siburtest.services;

import com.example.siburtest.domains.Order;

import java.util.List;

public interface CustomerService {
    List<Order> getOrders(Integer customerId);
    Order getOrder(Integer customerId, Integer orderId);
    void createOrder(Integer customerId, Integer transportTypeId, String fullname, String phone);
}
