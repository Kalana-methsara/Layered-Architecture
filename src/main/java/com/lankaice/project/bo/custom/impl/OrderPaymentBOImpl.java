package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.OrderPaymentBO;
import com.lankaice.project.bo.util.EntityDTOConverter;
import com.lankaice.project.dao.custom.OrderPaymentDAO;
import com.lankaice.project.dao.custom.impl.OrderPaymentDAOImpl;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.OrderPayment;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderPaymentBOImpl implements OrderPaymentBO {

    private final OrderPaymentDAO orderPaymentDAO = new OrderPaymentDAOImpl();
    private final EntityDTOConverter converter = new EntityDTOConverter();



    @Override
    public List<OrderPaymentDto> getAllPayments() throws SQLException, ClassNotFoundException {
        List<OrderPayment> payments = orderPaymentDAO.getAll(); // get all entities
        return payments.stream()
                .map(converter::getOrderPaymentDto) // convert each to DTO
                .toList(); // collect as List<OrderPaymentDto>
    }


    @Override
    public void addPayment(OrderPaymentDto orderPayments) throws SQLException, ClassNotFoundException {
        OrderPayment orderPayment =converter.getOrderPayment(orderPayments);
        orderPaymentDAO.save(orderPayment);
    }

    @Override
    public boolean updatePayment(OrderPayment orderPayment) throws SQLException, ClassNotFoundException {
        return orderPaymentDAO.update(orderPayment);
    }

    @Override
    public boolean deletePayment(String paymentId) throws SQLException, ClassNotFoundException {
        return orderPaymentDAO.delete(paymentId);
    }

    @Override
    public OrderPayment findPaymentById(String paymentId) throws SQLException, ClassNotFoundException {
        Optional<OrderPayment> optional = orderPaymentDAO.findById(paymentId);
        return optional.orElse(null);
    }

    @Override
    public List<OrderPaymentDto> searchPayments(String keyword) throws SQLException, ClassNotFoundException {
        return List.of();
    }


    @Override
    public boolean isPaymentCompleted(int orderId) throws SQLException, ClassNotFoundException {
        return orderPaymentDAO.isPaymentCompleted(orderId);
    }

    @Override
    public String getNextPaymentId() throws SQLException, ClassNotFoundException {
        return orderPaymentDAO.getNextPaymentId();
    }
}
