package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.BookingDAO;
import com.lankaice.project.entity.Booking;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookingDAOImpl implements BookingDAO {
    @Override
    public List<Booking> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public boolean save(Booking booking) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Booking booking) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<Booking> findById(String id) throws SQLException {
        return Optional.empty();
    }
}
