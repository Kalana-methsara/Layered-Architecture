package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.OrderDetailsDto;
import com.lankaice.project.entity.OrderDetails;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public interface OrderDetailsBO extends SuperBO {
    boolean saveOrderDetails(OrderDetailsDto dto) throws SQLException, ClassNotFoundException;

    public int todaySale() throws SQLException, ClassNotFoundException;

    public Map<LocalDate, Integer> getSalesForLast7Days() throws SQLException;
}
