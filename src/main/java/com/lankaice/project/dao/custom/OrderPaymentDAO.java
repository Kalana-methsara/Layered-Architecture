package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.OrderPayment;

import java.sql.SQLException;
import java.util.List;

public interface OrderPaymentDAO extends CrudDAO<OrderPayment> {
    List<OrderPayment> searchPayments(String keyword)throws SQLException, ClassNotFoundException;

    boolean isPaymentCompleted(int orderId) throws SQLException, ClassNotFoundException;

    String getNextPaymentId() throws SQLException, ClassNotFoundException;

    }
