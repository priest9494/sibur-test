package com.example.siburtest.repositories;

import com.example.siburtest.domains.Order;
import com.example.siburtest.exceptions.OrderException;

import java.util.List;

public interface ProviderRepository {
    List<Order> getOrders() throws OrderException;
    void changeStatus(Integer orderId, String status) throws OrderException;
}
