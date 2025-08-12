package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.PendingOrderDAO;
import com.lankaice.project.entity.PendingOrder;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PendingOrderDAOImpl implements PendingOrderDAO {
    @Override
    public List<PendingOrder> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(PendingOrder pendingOrder) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(PendingOrder pendingOrder) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<PendingOrder> findById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }
}
