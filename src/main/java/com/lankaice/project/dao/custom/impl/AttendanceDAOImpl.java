package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.AttendanceDAO;
import com.lankaice.project.entity.Attendance;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public List<Attendance> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String getNextId() {
        return "";
    }

    @Override
    public boolean save(Attendance attendance) {
        return false;
    }

    @Override
    public boolean update(Attendance attendance) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<String> getAllIds() {
        return List.of();
    }

    @Override
    public Optional<Attendance> findById(String id) {
        return Optional.empty();
    }
}
