package com.example.siburtest.services;

import com.example.siburtest.domains.Order;
import com.example.siburtest.exceptions.OrderException;

import java.util.List;

public interface OrderService {
    public List<Order> getOrders() throws OrderException;
}
