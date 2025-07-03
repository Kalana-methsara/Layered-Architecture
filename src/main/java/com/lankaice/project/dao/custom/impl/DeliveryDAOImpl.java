package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.DeliveryDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.entity.Delivery;

import java.sql.SQLException;
import java.util.List;

public class DeliveryDAOImpl implements DeliveryDAO {
    @Override
    public List<Delivery> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(Delivery delivery) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Delivery (order_id, delivery_date, delivery_time, delivery_address, delivery_status,ehicle_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return SQLUtil.execute(sql,
                delivery.getOrderId(),
                delivery.getDeliveryDate(),
                delivery.getDeliveryTime(),
                delivery.getDeliveryAddress(),
                delivery.getDeliveryState(),
                delivery.getVehicleId()
        );
    }

    @Override
    public boolean update(Delivery delivery) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
