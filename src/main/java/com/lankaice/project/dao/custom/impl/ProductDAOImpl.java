package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.ProductDAO;
import com.lankaice.project.entity.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
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

    @Override
    public List<String> getAllIds() {
        return List.of();
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }
}
