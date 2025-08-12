package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.EmployeeBO;
import com.lankaice.project.dao.custom.CustomerDAO;
import com.lankaice.project.dao.custom.EmployeeDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;

import java.sql.SQLException;

public class EmployeeBOImpl implements EmployeeBO {
    private final EmployeeDAO employeeDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.EMPLOYEE);


    @Override
    public String findNameById(String employeeId) throws SQLException, ClassNotFoundException {
        return employeeDAO.findNameById(employeeId);
    }
}
