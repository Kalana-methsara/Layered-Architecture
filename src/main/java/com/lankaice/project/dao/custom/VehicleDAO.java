package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDAO extends CrudDAO<Vehicle> {
    List<Vehicle> activeVehicles() throws SQLException, ClassNotFoundException;

    List<String> getActiveVehicle() throws SQLException, ClassNotFoundException;

    String getVehicleId(String vehicleNumber) throws SQLException, ClassNotFoundException;

    boolean setActiveVehicle(String vehicleNumber) throws SQLException, ClassNotFoundException;

    boolean setUnderRepairVehicle(String vehicleNumber) throws SQLException, ClassNotFoundException;


    boolean updates(String vehicleId, String inactive) throws SQLException, ClassNotFoundException;
}
