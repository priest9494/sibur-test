package com.example.siburtest.repositories;

import com.example.siburtest.domains.Order;
import com.example.siburtest.exceptions.OrderException;

import java.util.List;

public interface CustomerRepository {
    Order getOrderById(Integer customerId, Integer orderId) throws OrderException;
    List<Order> getAllOrders(Integer customerId) throws OrderException;
    void createOrder(Integer customerId, Integer transportTypeId, String fullname, String phone) throws  OrderException;
}
