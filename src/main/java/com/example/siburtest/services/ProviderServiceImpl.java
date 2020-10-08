package com.example.siburtest.services;

import com.example.siburtest.domains.Order;
import com.example.siburtest.exceptions.OrderException;
import com.example.siburtest.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    ProviderRepository providerRepository;

    @Override
    public List<Order> getOrders() throws OrderException {
        return providerRepository.getOrders();
    }

    @Override
    public void changeStatus(Integer orderId, String status) throws OrderException {
        if(status != "DONE" || status != "DECLINE")
            throw new OrderException("Invalid status");
        providerRepository.changeStatus(orderId, status);
    }
}
