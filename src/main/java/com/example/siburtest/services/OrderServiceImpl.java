package com.example.siburtest.services;

import com.example.siburtest.domains.Order;
import com.example.siburtest.exceptions.OrderException;
import com.example.siburtest.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getOrders() throws OrderException {
        return orderRepository.getOrders();
    }
}
