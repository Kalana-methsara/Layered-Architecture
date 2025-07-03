package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.OrdersDAO;
import com.lankaice.project.entity.Orders;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public List<Orders> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(Orders orders) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders orders) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

}
