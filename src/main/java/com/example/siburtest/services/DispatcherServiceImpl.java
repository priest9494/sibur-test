package com.example.siburtest.services;

import com.example.siburtest.domains.Order;
import com.example.siburtest.exceptions.OrderException;
import com.example.siburtest.repositories.DispatcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispatcherServiceImpl implements DispatcherService{
    @Autowired
    DispatcherRepository dispatcherRepository;

    @Override
    public List<Order> getOrders() throws OrderException {
        return dispatcherRepository.getOrders();
    }

    @Override
    public void changeStatus(Integer orderId, String status) throws OrderException {
        if(!status.equals("IN_PROGRESS") && !status.equals("DECLINE"))
            throw new OrderException("Invalid status");
        dispatcherRepository.changeStatus(orderId, status);
    }

}
