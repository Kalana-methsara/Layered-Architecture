package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.ProductBO;
import com.lankaice.project.bo.util.EntityDTOConverter;
import com.lankaice.project.dao.custom.ProductDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.ProductDto;
import com.lankaice.project.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {

    private final ProductDAO productDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.PRODUCT);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<ProductDto> getAllProducts() throws SQLException, ClassNotFoundException {
        List<Product> entityList = productDAO.getAll();
        List<ProductDto> dtoList = new ArrayList<>();

        for (Product entity : entityList) {
            dtoList.add(converter.getProductDTO(entity));
        }
        return dtoList;
    }

    @Override
    public String findIdByName(String productName) throws SQLException, ClassNotFoundException {
        return productDAO.findIdByName(productName);
    }

    @Override
    public boolean updateProductPrice(String productId, double newPrice) throws SQLException, ClassNotFoundException {
        return productDAO.updateProductPrice(productId, newPrice);
    }

    @Override
    public String findNameById(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.findNameById(productId);
    }

    @Override
    public List<String> getAllProductIds() throws SQLException, ClassNotFoundException {
        return productDAO.getAllProductIds();
    }
}
