package com.example.siburtest.repositories;

import com.example.siburtest.domains.Order;
import com.example.siburtest.exceptions.OrderException;

import java.util.List;

public interface OrderRepository {
    public List<Order> getOrders() throws OrderException;
}
