package com.lankaice.project.bo.custom;

import com.lankaice.project.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO {
    List<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;
}
