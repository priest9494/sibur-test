package com.example.siburtest.repositories;

import com.example.siburtest.domains.Customer;
import com.example.siburtest.exceptions.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class CustomerAuthRepositoryImpl implements CustomerAuthRepository {
    private static final String SQL_CREATE = "INSERT INTO customer(username, password) VALUES (?, ?)";
    private static final String SQL_COUNT_BY_USERNAME = "SELECT COUNT(*) FROM customer WHERE username = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM customer WHERE id = ?";
    private static final String SQL_FIND_BY_USERNAME = "SELECT * FROM customer WHERE username = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String username, String password) throws CustomerException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, password);

                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("id");
        } catch (Exception e) {
            throw new CustomerException("Failed to create account");
        }
    }

    @Override
    public Customer findByUsernameAndPassword(String username, String password) throws CustomerException {
        try {
            Customer customer = jdbcTemplate.queryForObject(SQL_FIND_BY_USERNAME, new Object[]{username}, customerRowMapper);
            if(!password.equals(customer.getPassword()))
                throw new CustomerException("Invalid username/password");
            return customer;
        } catch (EmptyResultDataAccessException e) {
            throw new CustomerException("Invalid username/password");
        }
    }

    @Override
    public Integer getCountByUsername(String username) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_USERNAME, new Object[]{username}, Integer.class);
    }

    @Override
    public Customer findById(Integer customerId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{customerId}, customerRowMapper);
    }

    private RowMapper<Customer> customerRowMapper = ((rs, rowNum) -> {
       return new Customer(rs.getInt("id"),
               rs.getString("username"),
               rs.getString("password"));
    });
}
