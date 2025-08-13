package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.SMSEmailDAO;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.SMSEmail;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SMSEmailDAOImpl implements SMSEmailDAO {
    @Override
    public List<SMSEmail> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(SMSEmail smsEmail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SMSEmail smsEmail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<SMSEmail> findById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }
}
