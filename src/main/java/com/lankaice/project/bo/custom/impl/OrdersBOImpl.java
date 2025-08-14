package com.lankaice.project.bo.custom.impl;


import com.lankaice.project.bo.custom.OrdersBO;
import com.lankaice.project.bo.exception.DuplicateException;
import com.lankaice.project.bo.exception.NotFoundException;
import com.lankaice.project.bo.util.EntityDTOConverter;
import com.lankaice.project.dao.custom.OrdersDAO;
import com.lankaice.project.dao.custom.QueryDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.OrdersDto;
import com.lankaice.project.dto.PendingOrderDto;
import com.lankaice.project.entity.Orders;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdersBOImpl implements OrdersBO {

    private final OrdersDAO ordersDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.ORDERS);
    private final QueryDAO queryDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.QUERY);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<OrdersDto> getAllOrders() throws SQLException, ClassNotFoundException {
        List<Orders> ordersList = ordersDAO.getAll();
        List<OrdersDto> dtoList = new ArrayList<>();
        for (Orders order : ordersList) {
            dtoList.add(converter.getOrdersDto(order));
        }
        return dtoList;
    }

    @Override
    public boolean saveOrder(OrdersDto dto) throws SQLException, ClassNotFoundException {
        Optional<Orders> optionalOrder = ordersDAO.findById(String.valueOf(dto.getOrderId()));
        if (optionalOrder.isPresent()) {
            throw new DuplicateException("Duplicate order ID");
        }

        Orders entity = converter.getOrders(dto);
        ordersDAO.save(entity);
        return false;
    }

    @Override
    public boolean updateOrder(OrdersDto dto) throws SQLException, ClassNotFoundException {
        Optional<Orders> optionalOrder = ordersDAO.findById(String.valueOf(dto.getOrderId()));
        if (optionalOrder.isEmpty()) {
            throw new NotFoundException("Order not found");
        }

        Orders entity = converter.getOrders(dto);
        ordersDAO.update(entity);
        return false;
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        Optional<Orders> optionalOrder = ordersDAO.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new NotFoundException("Order not found");
        }

        ordersDAO.delete(id);
        return false;
    }

    @Override
    public Optional<OrdersDto> findOrderById(String id) throws SQLException, ClassNotFoundException {
        Optional<Orders> optionalOrder = ordersDAO.findById(id);
        return optionalOrder.map(converter::getOrdersDto);
    }

    @Override
    public int getLastOrderId() throws SQLException, ClassNotFoundException {
        return ordersDAO.getLastOrderId();
    }

    @Override
    public boolean updateOrderStatus(int orderId, String productName, String newStatus) throws SQLException, ClassNotFoundException {
        return ordersDAO.updateOrderStatus(orderId, productName, newStatus);
    }

    @Override
    public boolean existOrdersByCustomerId(String customerId) throws SQLException, ClassNotFoundException {
        return ordersDAO.existOrdersByCustomerId(customerId);
    }

    @Override
    public ArrayList<PendingOrderDto> getPendingOrdersByDate(LocalDate now) throws SQLException, ClassNotFoundException {
        return queryDAO.getPendingOrdersByDate(now);
    }
}
