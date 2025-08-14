package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.SuperDAO;
import com.lankaice.project.dto.PendingOrderDto;
import com.lankaice.project.entity.CustomOrder;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomOrder> findFullOrderDataByCustomerId(String customerId) throws SQLException, ClassNotFoundException;
    public ArrayList<PendingOrderDto> getPendingOrdersByDate(LocalDate date) throws SQLException, ClassNotFoundException;
}
