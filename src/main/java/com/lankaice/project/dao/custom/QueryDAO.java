package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.SuperDAO;
import com.lankaice.project.entity.CustomOrder;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomOrder> findFullOrderDataByCustomerId(String customerId) throws SQLException, ClassNotFoundException;
}
