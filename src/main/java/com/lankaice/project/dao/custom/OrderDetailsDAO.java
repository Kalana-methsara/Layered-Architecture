package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.OrderDetails;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails> {
    public int todaySale() throws SQLException, ClassNotFoundException;

    public Map<LocalDate, Integer> getSalesForLast7Days() throws SQLException;
}
