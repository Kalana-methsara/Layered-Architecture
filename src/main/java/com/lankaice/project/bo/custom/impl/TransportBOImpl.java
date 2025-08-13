package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.TransportBO;
import com.lankaice.project.bo.util.EntityDTOConverter;
import com.lankaice.project.dao.custom.TransportDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.TransportDto;
import com.lankaice.project.entity.Transport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransportBOImpl implements TransportBO {

    private final TransportDAO transportDAO = (TransportDAO) DAOFactoryImpl.getInstance().getDAO(DAOType.TRANSPORT);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<TransportDto> getAllTransports() throws SQLException, ClassNotFoundException {
        List<Transport> list = transportDAO.getAll();
        List<TransportDto> dtoList = new ArrayList<>();
        for (Transport transport : list) {
            dtoList.add(converter.getTransportDto(transport));
        }
        return dtoList;
    }

    @Override
    public boolean saveTransport(TransportDto dto) throws SQLException, ClassNotFoundException {
        return transportDAO.save(converter.getTransport(dto));
    }

    @Override
    public boolean updateTransport(TransportDto dto) throws SQLException, ClassNotFoundException {
        return transportDAO.update(converter.getTransport(dto));
    }

    @Override
    public boolean deleteTransport(String transportId) throws SQLException, ClassNotFoundException {
        return transportDAO.delete(transportId);
    }

    @Override
    public TransportDto findTransportById(String id) throws SQLException, ClassNotFoundException {
        return transportDAO.findById(id)
                .map(converter::getTransportDto)
                .orElse(null);
    }

    @Override
    public int getTodayTransportTotal() throws SQLException, ClassNotFoundException {
        return transportDAO.getTodayTransportTotal();
    }

    @Override
    public List<TransportDto> getTransportByDate(String date) throws SQLException, ClassNotFoundException {
        List<Transport> list = transportDAO.getTransportByDate(date);
        List<TransportDto> dtoList = new ArrayList<>();
        for (Transport transport : list) {
            dtoList.add(converter.getTransportDto(transport));
        }
        return dtoList;
    }

    @Override
    public String getNextTransportId() throws SQLException, ClassNotFoundException {
        return transportDAO.getNextId();
    }
}
