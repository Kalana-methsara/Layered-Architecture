package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.InventoryCartBO;
import com.lankaice.project.bo.util.EntityDTOConverter;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dao.custom.InventoryCartDAO;
import com.lankaice.project.dto.tm.CartItemTM;
import com.lankaice.project.entity.InventoryCart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventoryCartBOImpl implements InventoryCartBO {

    private final InventoryCartDAO inventoryCartDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.INVENTORY_CART);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<CartItemTM> getAllInventoryCart() throws SQLException, ClassNotFoundException {
        List<InventoryCart> entityList = inventoryCartDAO.getAll();
        List<CartItemTM> dtoList = new ArrayList<>();
        for (InventoryCart entity : entityList) {
            dtoList.add(converter.getInventoryCartDto(entity));
        }
        return dtoList;
    }

    @Override
    public void saveInventoryCart(CartItemTM dto) throws SQLException, ClassNotFoundException {
        InventoryCart entity = converter.getInventoryCart(dto);
        inventoryCartDAO.save(entity);
    }

    @Override
    public void deleteInventoryCart(String cartId) throws SQLException, ClassNotFoundException {
        inventoryCartDAO.delete(cartId);
    }

    @Override
    public Optional<CartItemTM> findInventoryCartById(String id) throws SQLException, ClassNotFoundException {
        Optional<InventoryCart> entityOpt = inventoryCartDAO.findById(id);
        return entityOpt.map(converter::getInventoryCartDto);
    }

    @Override
    public void updateCartItem(CartItemTM dto, double newQty) throws SQLException, ClassNotFoundException {
        InventoryCart entity = converter.getInventoryCart(dto);
        inventoryCartDAO.updateCartItem(entity, newQty);
    }

}
