package com.example.siburtest.repositories;

import com.example.siburtest.domains.Order;
import com.example.siburtest.exceptions.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository{
    private static final String SQL_GET_ORDERS = "SELECT * FROM ORDERS";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> getOrders() throws OrderException {
        try {
            return jdbcTemplate.query(SQL_GET_ORDERS, orderRowMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new OrderException("Can't get order list");
        }
    }

    private RowMapper<Order> orderRowMapper = ((rs, rowNum) -> {
        Integer transportId = rs.getInt("transportId");
        if (transportId == 0)
            transportId = null;
        return new Order(rs.getInt("id"),
                rs.getInt("customerId"),
                rs.getInt("transportTypeId"),
                transportId,
                rs.getString("fullname"),
                rs.getString("phone"),
                rs.getString("status"));
    });
}
