package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.OrdersDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdersDAOImpl implements OrdersDAO {

    @Override
    public List<Orders> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Orders");
        List<Orders> ordersList = new ArrayList<>();

        while (resultSet.next()) {
            Orders order = new Orders(
                    resultSet.getInt("order_id"),
                    resultSet.getString("customer_id"),
                    resultSet.getDate("order_date").toString(),
                    resultSet.getTime("order_time").toString(),
                    resultSet.getString("description"),
                    resultSet.getString("vehicle_number"),
                    resultSet.getDouble("total_amount")
            );
            ordersList.add(order);
        }
        return ordersList;
    }

    @Override
    public boolean save(Orders order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Orders (order_id,customer_id, order_date, order_time, description, vehicle_number, total_amount) VALUES (?, ? , ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql,
                order.getOrderId(),
                order.getCustomerId(),
                order.getOrderDate(),
                order.getOrderTime(),
                order.getDescription(),
                order.getVehicle_number(),
                order.getTotalAmount()
        );
    }

    @Override
    public boolean update(Orders order) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Orders SET customer_id = ?, order_date = ?, order_time = ?, description = ?, vehicle_number = ?, total_amount = ? WHERE order_id = ?";
        return SQLUtil.execute(sql,
                order.getCustomerId(),
                order.getOrderDate(),
                order.getOrderTime(),
                order.getDescription(),
                order.getVehicle_number(),
                order.getTotalAmount(),
                order.getOrderId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Orders WHERE order_id = ?", id);
    }

    @Override
    public Optional<Orders> findById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Orders WHERE order_id = ?", id);
        if (rs.next()) {
            Orders order = new Orders(
                    rs.getInt("order_id"),
                    rs.getString("customer_id"),
                    rs.getDate("order_date").toString(),
                    rs.getTime("order_time").toString(),
                    rs.getString("description"),
                    rs.getString("vehicle_number"),
                    rs.getDouble("total_amount")
            );
            return Optional.of(order);
        }
        return Optional.empty();
    }
    @Override
    public int getLastOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT MAX(order_id) AS order_id FROM Orders");
        if (rs.next()) {
            int lastId = rs.getInt("order_id");
            return rs.wasNull() ? 1001 : lastId;
        }
        return 1001;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String productName, String newStatus) throws SQLException, ClassNotFoundException {
        // You need product ID lookup here; placeholder logic
        String sql = "UPDATE PendingOrder SET status = ? WHERE order_id = ? AND product_id = (SELECT product_id FROM Product WHERE name = ?)";
        return SQLUtil.execute(sql, newStatus, orderId, productName);
    }

    @Override
    public boolean existOrdersByCustomerId(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT 1 FROM Orders WHERE customer_id = ? LIMIT 1", customerId);
        return resultSet.next();
    }
}
