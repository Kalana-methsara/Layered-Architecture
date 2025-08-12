package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.tm.CartItemTM;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface InventoryCartBO extends SuperBO {
    List<CartItemTM> getAllInventoryCart() throws SQLException, ClassNotFoundException;

    void saveInventoryCart(CartItemTM dto) throws SQLException, ClassNotFoundException;

    void deleteInventoryCart(String cartId) throws SQLException, ClassNotFoundException;

    Optional<CartItemTM> findInventoryCartById(String id) throws SQLException, ClassNotFoundException;

    void updateCartItem(CartItemTM dto, double newQty) throws SQLException, ClassNotFoundException;

}
