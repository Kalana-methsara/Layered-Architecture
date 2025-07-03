package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.dto.tm.CartItemTM;
import com.lankaice.project.entity.InventoryCart;

import java.sql.SQLException;

public interface InventoryCartDAO extends CrudDAO<InventoryCart> {
    boolean updateCartItem(InventoryCart item, double newQty) throws SQLException, ClassNotFoundException;
}
