package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.OrderPaymentDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.entity.OrderPayment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderPaymentDAOImpl implements OrderPaymentDAO {
    @Override
    public List<OrderPayment> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Order_Payment";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<OrderPayment> list = new ArrayList<>();

        while (resultSet.next()) {
            OrderPayment dto = new OrderPayment(
                    resultSet.getString("payment_id"),
                    resultSet.getInt("order_id"),
                    resultSet.getString("payment_method"),
                    resultSet.getInt("items_count"),
                    resultSet.getDouble("subtotal"),
                    resultSet.getDouble("discount"),
                    resultSet.getDouble("net_total"),
                    resultSet.getString("payment_date"),
                    resultSet.getString("status")
            );
            list.add(dto);
        }
        return list;
    }

    @Override
    public boolean save(OrderPayment orderPayment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Order_Payment (payment_id, order_id, payment_method, items_count, subtotal, discount, net_total, payment_date, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(
                sql,
                orderPayment.getPaymentId(),
                orderPayment.getOrderId(),
                orderPayment.getPaymentMethod(),
                orderPayment.getItemCount(),
                orderPayment.getSubtotal(),
                orderPayment.getDiscount(),
                orderPayment.getNetTotal(),
                orderPayment.getPaymentDate(),
                orderPayment.getStatus()
        );
    }

    @Override
    public boolean update(OrderPayment orderPayment) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Order_Payment SET order_id = ?, payment_method = ?, items_count = ?, subtotal = ?, discount = ?, net_total = ?, payment_date = ?, status = ? " +
                "WHERE payment_id = ?";
        return SQLUtil.execute(
                sql,
                orderPayment.getOrderId(),
                orderPayment.getPaymentMethod(),
                orderPayment.getItemCount(),
                orderPayment.getSubtotal(),
                orderPayment.getDiscount(),
                orderPayment.getNetTotal(),
                orderPayment.getPaymentDate(),
                orderPayment.getStatus(),
                orderPayment.getPaymentId()
        );
    }

    @Override
    public boolean delete(String paymentId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Order_Payment WHERE payment_id = ?";
        return SQLUtil.execute(sql, paymentId);
    }

    @Override
    public Optional<OrderPayment> findById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public List<OrderPayment> searchPayments(String keyword) throws SQLException, ClassNotFoundException {
        List<OrderPayment> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Order_Payment WHERE " +
                    "payment_id LIKE ? OR " +
                    "order_id LIKE ? OR " +
                    "payment_method LIKE ? OR " +
                    "status LIKE ?";
            ResultSet rs = SQLUtil.execute(sql,
                    "%" + keyword + "%",
                    "%" + keyword + "%",
                    "%" + keyword + "%",
                    "%" + keyword + "%");

            while (rs.next()) {
                OrderPayment dto = new OrderPayment(
                        rs.getString("payment_id"),
                        rs.getInt("order_id"),
                        rs.getString("payment_method"),
                        rs.getInt("items_count"),
                        rs.getDouble("subtotal"),
                        rs.getDouble("discount"),
                        rs.getDouble("net_total"),
                        rs.getString("payment_date"),
                        rs.getString("status")
                );
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean isPaymentCompleted(int orderId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT 1 FROM Order_Payment WHERE order_id = ? AND status = 'Success'";
        ResultSet resultSet = SQLUtil.execute(sql, orderId);
        return resultSet.next();
    }

    @Override
    public String getNextPaymentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT payment_id FROM Order_Payment ORDER BY payment_id DESC LIMIT 1");
        String prefix = "PAY";

        if (resultSet.next()) {
            String lastId = resultSet.getString("payment_id");
            String numberPart = lastId.substring(3);

            try {
                int lastNumber = Integer.parseInt(numberPart);
                int nextNumber = lastNumber + 1;
                return String.format("%s%03d", prefix, nextNumber);
            } catch (NumberFormatException e) {
                return prefix + "001";
            }
        }

        return prefix + "001";

    }
}
