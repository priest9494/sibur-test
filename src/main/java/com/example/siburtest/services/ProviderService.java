package com.example.siburtest.services;

import com.example.siburtest.domains.Order;
import com.example.siburtest.exceptions.OrderException;

import java.util.List;

public interface ProviderService {
    List<Order> getOrders() throws OrderException;
    void setTransport(Integer orderId, Integer transportId) throws OrderException;
    void declineOrder(Integer orderId) throws OrderException;
}
