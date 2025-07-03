package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.ProductDAO;
import com.lankaice.project.entity.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(Product product) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

}
