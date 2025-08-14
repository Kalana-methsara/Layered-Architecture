package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.Delivery;

import java.sql.SQLException;

public interface DeliveryDAO extends CrudDAO<Delivery> {
    boolean save(int orderId, String orderDate, String orderTime, String galle, String pending, String vehicleId) throws SQLException, ClassNotFoundException;
}
