package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.dto.TransportDto;
import com.lankaice.project.entity.Transport;

import java.sql.SQLException;
import java.util.List;

public interface TransportDAO extends CrudDAO<Transport> {
    int getTodayTransportTotal() throws SQLException, ClassNotFoundException;

    List<Transport> getTransportByDate(String date) throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

}
