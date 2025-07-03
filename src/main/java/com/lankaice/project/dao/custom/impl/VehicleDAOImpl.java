package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.VehicleDAO;
import com.lankaice.project.entity.Vehicle;

import java.sql.SQLException;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public List<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean save(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
