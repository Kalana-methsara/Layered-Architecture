package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    List<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;
}
