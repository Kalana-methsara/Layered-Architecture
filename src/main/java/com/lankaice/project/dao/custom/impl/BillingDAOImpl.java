package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.BillingDAO;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.Billing;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BillingDAOImpl implements BillingDAO {
    @Override
    public List<Billing> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(Billing billing) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Billing billing) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<Billing> findById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

}
