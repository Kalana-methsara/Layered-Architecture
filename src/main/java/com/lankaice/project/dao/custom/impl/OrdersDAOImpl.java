package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.OrdersDAO;
import com.lankaice.project.entity.Orders;

import java.util.List;
import java.util.Optional;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public List<Orders> getAll() {
        return List.of();
    }

    @Override
    public String getNextId() {
        return "";
    }

    @Override
    public boolean save(Orders orders) {
        return false;
    }

    @Override
    public boolean update(Orders orders) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<String> getAllIds() {
        return List.of();
    }

    @Override
    public Optional<Orders> findById(String id) {
        return Optional.empty();
    }
}
