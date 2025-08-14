package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.OrderDetailsBO;
import com.lankaice.project.bo.util.EntityDTOConverter;
import com.lankaice.project.dao.custom.OrderDetailsDAO;
import com.lankaice.project.dao.custom.OrdersDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.OrderDetailsDto;
import com.lankaice.project.entity.OrderDetails;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    private final OrderDetailsDAO orderDetailsDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.ORDER_DETAIL);

    private final EntityDTOConverter converter = new EntityDTOConverter();


    @Override
    public boolean saveOrderDetails(OrderDetailsDto dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.save(converter.getOrderDetailsEntity(dto));
    }

    @Override
    public int todaySale() throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.todaySale();
    }

    @Override
    public Map<LocalDate, Integer> getSalesForLast7Days() throws SQLException {
        return orderDetailsDAO.getSalesForLast7Days();
    }
}
