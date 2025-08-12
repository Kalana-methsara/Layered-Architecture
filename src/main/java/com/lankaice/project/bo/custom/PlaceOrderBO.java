package com.lankaice.project.bo.custom;

import com.lankaice.project.dto.OrdersDto;

import java.sql.SQLException;

public interface PlaceOrderBO extends SalaryBO{

    boolean placeOrder(OrdersDto dto) throws SQLException, ClassNotFoundException;
}
