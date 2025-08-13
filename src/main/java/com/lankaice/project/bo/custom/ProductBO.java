package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.ProductDto;

import java.sql.SQLException;
import java.util.List;

public interface ProductBO extends SuperBO {

    List<ProductDto> getAllProducts() throws SQLException, ClassNotFoundException;

    String findIdByName(String productName) throws SQLException, ClassNotFoundException;

    boolean updateProductPrice(String productId, double newPrice) throws SQLException, ClassNotFoundException;

    String findNameById(String productId) throws SQLException, ClassNotFoundException;

    List<String> getAllProductIds() throws SQLException, ClassNotFoundException;
}
