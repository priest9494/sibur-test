package com.example.siburtest.repositories;

import com.example.siburtest.domains.Order;
import com.example.siburtest.exceptions.CustomerException;
import com.example.siburtest.exceptions.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements  CustomerRepository {
    private static final String SQL_GET_ORDERS_BY_CUSTOMER_ID = "SELECT * FROM orders WHERE customerId = ?";
    private static final String SQL_GET_ORDER_BY_CUSTOMER_ID_AND_ORDER_ID = "SELECT * FROM orders WHERE customerId = ? AND id = ?";
    private static final String SQL_CREATE_ORDER = "INSERT INTO orders (customerId, transportTypeId, fullname, phone, status) VALUES (?, ?, ?, ?, 'NEW')";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Order getOrderById(Integer customerId, Integer orderId) {
        try {
            Order order = jdbcTemplate.queryForObject(SQL_GET_ORDER_BY_CUSTOMER_ID_AND_ORDER_ID, new Object[]{customerId, orderId}, orderRowMapper);
            return order;
        } catch (EmptyResultDataAccessException e) {
            throw new OrderException("Can't get order list");
        }
    }

    @Override
    public List<Order> getAllOrders(Integer customerId) throws CustomerException {
        try {
            return jdbcTemplate.query(SQL_GET_ORDERS_BY_CUSTOMER_ID, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setInt(1, customerId);
                }
            }, orderRowMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new OrderException("Can't get order list");
        }
    }

    @Override
    public void createOrder(Integer customerId, Integer transportTypeId, String fullname, String phone) throws CustomerException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, customerId);
                ps.setInt(2, transportTypeId);
                ps.setString(3, fullname);
                ps.setString(4, phone);
                return ps;
            }, keyHolder);
        } catch (Exception e) {
            throw new OrderException("Failed to create order");
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
