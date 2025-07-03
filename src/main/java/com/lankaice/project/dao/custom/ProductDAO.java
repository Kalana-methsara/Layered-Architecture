package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {
    String findIdByName(String productName) throws SQLException, ClassNotFoundException;

    boolean updateProductPrice(String productId, double newPrice) throws SQLException, ClassNotFoundException;

    String findNameById(String productId) throws SQLException, ClassNotFoundException;

    List<String> getAllProductIds() throws SQLException, ClassNotFoundException;
}
