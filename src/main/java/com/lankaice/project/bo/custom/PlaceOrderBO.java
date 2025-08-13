package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.OrdersDto;

import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {

    boolean placeOrder(OrdersDto dto) throws SQLException, ClassNotFoundException;
}
