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
    public void setTransport(Integer orderId, Integer transportId) throws OrderException {
        providerRepository.setTransport(orderId, transportId);
    }

    @Override
    public void declineOrder(Integer orderId) throws OrderException {
        providerRepository.declineOrder(orderId);
    }
}
