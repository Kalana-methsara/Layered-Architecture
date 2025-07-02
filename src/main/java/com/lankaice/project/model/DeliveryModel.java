package com.lankaice.project.model;

import com.lankaice.project.dto.DeliveryDto;
import com.lankaice.project.dao.util.SQLUtil;

import java.sql.SQLException;

public class DeliveryModel {
    public boolean saveDelivery(DeliveryDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Delivery (order_id, delivery_date, delivery_time, delivery_address, delivery_status,ehicle_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return SQLUtil.execute(sql,
                dto.getOrderId(),
                dto.getDeliveryDate(),
                dto.getDeliveryTime(),
                dto.getDeliveryAddress(),
                dto.getDeliveryState(),
                dto.getVehicleId()
        );
    }

}
