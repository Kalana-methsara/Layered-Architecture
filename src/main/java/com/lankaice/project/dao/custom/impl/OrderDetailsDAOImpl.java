package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.OrderDetailsDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public List<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }


    @Override
    public boolean save(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO Order_Details (order_id, product_id, quantity, unit_price, discount) VALUES (?,?,?,?,?)",
                orderDetails.getOrderId(),
                orderDetails.getProductId(),
                orderDetails.getQuantity(),
                orderDetails.getUnitPrice(),
                orderDetails.getDiscount());
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<OrderDetails> findById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

}
