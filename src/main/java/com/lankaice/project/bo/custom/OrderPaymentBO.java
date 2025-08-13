package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.OrderPayment;

import java.sql.SQLException;
import java.util.List;

public interface OrderPaymentBO extends SuperBO {
    List<OrderPaymentDto> getAllPayments() throws SQLException, ClassNotFoundException;

    void addPayment(OrderPaymentDto orderPayment) throws SQLException, ClassNotFoundException;

    boolean updatePayment(OrderPayment orderPayment) throws SQLException, ClassNotFoundException;

    boolean deletePayment(String paymentId) throws SQLException, ClassNotFoundException;

    OrderPayment findPaymentById(String paymentId) throws SQLException, ClassNotFoundException;

    List<OrderPaymentDto> searchPayments(String keyword) throws SQLException, ClassNotFoundException;

    boolean isPaymentCompleted(int orderId) throws SQLException, ClassNotFoundException;

    String getNextPaymentId() throws SQLException, ClassNotFoundException;

}
