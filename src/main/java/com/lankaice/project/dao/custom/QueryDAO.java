package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.SuperDAO;

public interface QueryDAO extends SuperDAO {
    void findFullOrderDataByCustomerId(String customerId);
}
