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
public class ProviderRepositoryImpl implements ProviderRepository {
    private static final String SQL_GET_ORDERS = "SELECT * FROM orders WHERE status = 'IN_PROGRESS'";
    private static final String SQL_DECLINE_ORDER = "Update orders SET status = 'DECLINE' WHERE id = ?";
    private static final String SQL_SET_TRANSPORT = "Update orders SET status = 'DONE', transportId = ? WHERE id = ?";

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

    @Override
    public void setTransport(Integer orderId, Integer transportId) throws OrderException {
        try {
            jdbcTemplate.update(SQL_SET_TRANSPORT, transportId, orderId);
        } catch (Exception e) {
            throw new OrderException("Can't set transport");
        }
    }

    @Override
    public void declineOrder(Integer orderId) throws OrderException {
        try {
            jdbcTemplate.update(SQL_DECLINE_ORDER, orderId);
        } catch (Exception e) {
            throw new OrderException("Can't decline order");
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
