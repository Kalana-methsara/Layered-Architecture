package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.OrderDetailsDAO;
import com.lankaice.project.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public List<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }


    @Override
    public boolean save(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

}
