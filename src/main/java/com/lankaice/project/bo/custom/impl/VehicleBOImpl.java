package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.VehicleBO;
import com.lankaice.project.dao.custom.VehicleDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.VehicleDto;
import com.lankaice.project.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleBOImpl implements VehicleBO {

    private final VehicleDAO vehicleDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.VEHICLE);

    @Override
    public List<VehicleDto> getAllVehicles() throws SQLException, ClassNotFoundException {
        List<Vehicle> vehicleList = vehicleDAO.getAll();
        List<VehicleDto> dtoList = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            dtoList.add(new VehicleDto(
                    v.getVehicleId(),
                    v.getVehicleNumber(),
                    v.getStatus(),
                    v.getCapacity(),
                    v.getModel()
            ));
        }
        return dtoList;
    }

    @Override
    public List<VehicleDto> getActiveVehicles() throws SQLException, ClassNotFoundException {
        List<Vehicle> activeList = vehicleDAO.activeVehicles();
        List<VehicleDto> dtoList = new ArrayList<>();
        for (Vehicle v : activeList) {
            dtoList.add(new VehicleDto(
                    v.getVehicleId(),
                    v.getVehicleNumber(),
                    v.getStatus(),
                    v.getCapacity(),
                    v.getModel()
            ));
        }
        return dtoList;
    }

    @Override
    public List<String> getActiveVehicleNumbers() throws SQLException, ClassNotFoundException {
        return vehicleDAO.getActiveVehicle();
    }

    @Override
    public String getVehicleIdByNumber(String vehicleNumber) throws SQLException, ClassNotFoundException {
        return vehicleDAO.getVehicleId(vehicleNumber);
    }

    @Override
    public boolean setVehicleAsActive(String vehicleNumber) throws SQLException, ClassNotFoundException {
        return vehicleDAO.setActiveVehicle(vehicleNumber);
    }

    @Override
    public boolean setVehicleAsUnderRepair(String vehicleNumber) throws SQLException, ClassNotFoundException {
        return vehicleDAO.setUnderRepairVehicle(vehicleNumber);
    }
}
