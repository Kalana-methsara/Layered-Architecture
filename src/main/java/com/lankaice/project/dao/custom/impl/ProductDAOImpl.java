package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.ProductDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl implements ProductDAO {


    @Override
    public List<Product> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Product";
        ResultSet rs = SQLUtil.execute(sql);

        List<Product> productList = new ArrayList<>();
        while (rs.next()) {
            productList.add(new Product(
                    rs.getString("product_id"),
                    rs.getString("name"),
                    rs.getDouble("weight"),
                    rs.getDouble("price_per_unit")
            ));
        }
        return productList;    }

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
    public Optional<Product> findById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public String findIdByName(String productName) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT product_id FROM Product WHERE name = ?", productName);
        if (rs.next()) {
            return rs.getString("product_id");
        }
        return "";    }

    @Override
    public boolean updateProductPrice(String productId, double newPrice) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Product SET price_per_unit = ? WHERE product_id = ?";
        return SQLUtil.execute(sql, newPrice, productId);    }

    @Override
    public String findNameById(String productId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT name FROM Product WHERE product_id = ?", productId);
        if (rs.next()) {
            return rs.getString("name");
        }
        return "";    }

    @Override
    public List<String> getAllProductIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT product_id FROM Product");
        List<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("product_id"));
        }
        return list;
    }
}
