package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.Product;

import java.sql.SQLException;

public interface ProductDAO extends CrudDAO<Product> {
    String findIdByName(String productName) throws SQLException, ClassNotFoundException;
}
