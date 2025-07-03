package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.StockDAO;
import com.lankaice.project.entity.Stock;

import java.sql.SQLException;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    @Override
    public List<Stock> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(Stock stock) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Stock stock) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
