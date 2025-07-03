package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.Orders;

import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<Orders> {
    int getLastOrderId() throws SQLException, ClassNotFoundException;

    boolean updateOrderStatus(int orderId, String productName, String newStatus) throws SQLException, ClassNotFoundException;
}
