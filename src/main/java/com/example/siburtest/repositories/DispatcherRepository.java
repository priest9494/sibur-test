package com.example.siburtest.repositories;

import com.example.siburtest.domains.Order;
import com.example.siburtest.exceptions.OrderException;

import java.util.List;

public interface DispatcherRepository {
    void changeStatus(Integer orderId, String status) throws OrderException;
    List<Order> getOrders() throws OrderException;
}
