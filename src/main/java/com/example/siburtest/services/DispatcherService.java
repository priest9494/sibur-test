package com.example.siburtest.services;

import com.example.siburtest.domains.Order;
import com.example.siburtest.exceptions.OrderException;

import java.util.List;

public interface DispatcherService {
    List<Order> getOrders() throws OrderException;
    void changeStatus(Integer orderId, String status) throws OrderException;
}