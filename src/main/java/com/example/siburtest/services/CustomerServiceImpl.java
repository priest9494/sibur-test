package com.example.siburtest.services;

import com.example.siburtest.domains.Order;
import com.example.siburtest.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Order> getOrders(Integer customerId) {
        return customerRepository.getAllOrders(customerId);
    }

    @Override
    public Order getOrder(Integer customerId, Integer orderId) {
        return customerRepository.getOrderById(customerId, orderId);
    }

    @Override
    public void createOrder(Integer customerId, Integer transportTypeId, String fullname, String phone) {
        customerRepository.createOrder(customerId, transportTypeId, fullname, phone);
    }
}
