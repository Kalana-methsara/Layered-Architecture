package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;

import java.sql.SQLException;

public interface EmployeeBO extends SuperBO {
    String findNameById(String employeeId) throws SQLException, ClassNotFoundException;

}
