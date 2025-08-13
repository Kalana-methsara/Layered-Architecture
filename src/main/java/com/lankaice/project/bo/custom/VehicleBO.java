package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface VehicleBO extends SuperBO {

    List<VehicleDto> getAllVehicles() throws SQLException, ClassNotFoundException;

    List<VehicleDto> getActiveVehicles() throws SQLException, ClassNotFoundException;

    List<String> getActiveVehicleNumbers() throws SQLException, ClassNotFoundException;

    String getVehicleIdByNumber(String vehicleNumber) throws SQLException, ClassNotFoundException;

    boolean setVehicleAsActive(String vehicleNumber) throws SQLException, ClassNotFoundException;

    boolean setVehicleAsUnderRepair(String vehicleNumber) throws SQLException, ClassNotFoundException;
}
