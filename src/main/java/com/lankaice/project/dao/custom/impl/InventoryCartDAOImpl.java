package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.InventoryCartDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.InventoryCart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventoryCartDAOImpl implements InventoryCartDAO {
    @Override
    public List<InventoryCart> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Inventory_Cart");
        List<InventoryCart> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new InventoryCart(
                    resultSet.getInt("cart_id"),
                    resultSet.getString("supplier_id"),
                    resultSet.getInt("material_id"),
                    resultSet.getString("name"),
                    resultSet.getString("unit_type"),
                    resultSet.getDouble("unit_price"),
                    resultSet.getInt("quantity"),
                    resultSet.getDouble("total")
            ));
        }
        return list;
    }

    @Override
    public boolean save(InventoryCart inventoryCart) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Inventory_Cart (supplier_id, material_id,name, unit_type, unit_price, quantity) VALUES (?, ?, ?,?, ?, ?)";
        return SQLUtil.execute(sql,
                inventoryCart.getSupplierId(),
                inventoryCart.getMaterialId(),
                inventoryCart.getName(),
                inventoryCart.getUnitType(),
                inventoryCart.getUnitPrice(),
                inventoryCart.getQuantity()
        );
    }

    @Override
    public boolean update(InventoryCart inventoryCart) throws SQLException, ClassNotFoundException {
       return false;
    }

    @Override
    public boolean delete(String cartId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Inventory_Cart WHERE cart_id = ?";
        return SQLUtil.execute(sql, cartId);

    }

    @Override
    public Optional<InventoryCart> findById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Inventory_Cart WHERE cart_id = ?", id);
        if (resultSet.next()) {
            return Optional.of(new InventoryCart(
                    resultSet.getInt("cart_id"),
                    resultSet.getString("supplier_id"),
                    resultSet.getInt("material_id"),
                    resultSet.getString("name"),
                    resultSet.getString("unit_type"),
                    resultSet.getDouble("unit_price"),
                    resultSet.getInt("quantity"),
                    resultSet.getDouble("total")
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean updateCartItem(InventoryCart item, double newQty) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Inventory_Cart SET quantity = ?, unit_price = ?, unit_type = ? WHERE supplier_id = ? AND material_id = ?";
        return SQLUtil.execute(sql,
                newQty,
                item.getUnitPrice(),
                item.getUnitType(),
                item.getSupplierId(),
                item.getMaterialId()
        );
    }
}
