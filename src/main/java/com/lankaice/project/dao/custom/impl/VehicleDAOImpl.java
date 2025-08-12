package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.VehicleDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public List<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Vehicle");
        List<Vehicle> vehicles = new ArrayList<>();
        while (resultSet.next()) {
            Vehicle vehicle = new Vehicle(
                    resultSet.getInt("vehicle_id"),
                    resultSet.getString("vehicle_number"),
                    resultSet.getString("status"),
                    resultSet.getInt("capacity"),
                    resultSet.getString("model")
            );
            vehicles.add(vehicle);
        }
        return vehicles;
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

    @Override
    public Optional<Vehicle> findById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public List<Vehicle> activeVehicles() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Vehicle WHERE status = 'Active'");
        List<Vehicle> activeList = new ArrayList<>();
        while (resultSet.next()) {
            Vehicle vehicle = new Vehicle(
                    resultSet.getInt("vehicle_id"),
                    resultSet.getString("vehicle_number"),
                    resultSet.getString("status"),
                    resultSet.getInt("capacity"),
                    resultSet.getString("model")
            );
            activeList.add(vehicle);
        }
        return activeList;
    }

    @Override
    public List<String> getActiveVehicle() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT vehicle_number FROM Vehicle WHERE status = 'Active'");
        ArrayList<String> activeVehicleIds = new ArrayList<>();
        while (resultSet.next()) {
            activeVehicleIds.add(resultSet.getString("vehicle_number"));
        }
        return activeVehicleIds;
    }

    @Override
    public String getVehicleId(String vehicleNumber) throws SQLException, ClassNotFoundException {
        String sql = "SELECT vehicle_id FROM Vehicle WHERE vehicle_number = ?";

        ResultSet resultSet = SQLUtil.execute(sql, vehicleNumber);
        if (resultSet.next()) {
            return resultSet.getString("vehicle_id");
        }
        return null;
    }

    @Override
    public boolean setActiveVehicle(String vehicleNumber) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Vehicle SET status = 'Active' WHERE vehicle_number = ?", vehicleNumber);

    }

    @Override
    public boolean setUnderRepairVehicle(String vehicleNumber) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Vehicle SET status = 'Under Repair' WHERE vehicle_number = ?", vehicleNumber);
    }
}
