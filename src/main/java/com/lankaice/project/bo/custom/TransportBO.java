package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.TransportDto;

import java.sql.SQLException;
import java.util.List;

public interface TransportBO extends SuperBO {
    List<TransportDto> getAllTransports() throws SQLException, ClassNotFoundException;

    boolean saveTransport(TransportDto dto) throws SQLException, ClassNotFoundException;

    boolean updateTransport(TransportDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteTransport(String transportId) throws SQLException, ClassNotFoundException;

    TransportDto findTransportById(String id) throws SQLException, ClassNotFoundException;

    int getTodayTransportTotal() throws SQLException, ClassNotFoundException;

    List<TransportDto> getTransportByDate(String date) throws SQLException, ClassNotFoundException;

    String getNextTransportId() throws SQLException, ClassNotFoundException;

}
