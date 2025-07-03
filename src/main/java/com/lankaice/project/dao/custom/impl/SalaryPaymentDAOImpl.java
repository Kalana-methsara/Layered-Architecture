package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.SalaryPaymentDAO;
import com.lankaice.project.entity.SalaryPayment;

import java.sql.SQLException;
import java.util.List;

public class SalaryPaymentDAOImpl implements SalaryPaymentDAO {
    @Override
    public List<SalaryPayment> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(SalaryPayment salaryPayment) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SalaryPayment salaryPayment) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
