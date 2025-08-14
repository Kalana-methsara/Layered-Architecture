package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.OrderDetailsDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.db.DBConnection;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public List<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }


    @Override
    public boolean save(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO Order_Details (order_id, product_id, quantity, unit_price, discount) VALUES (?,?,?,?,?)",
                orderDetails.getOrderId(),
                orderDetails.getProductId(),
                orderDetails.getQuantity(),
                orderDetails.getUnitPrice(),
                orderDetails.getDiscount());
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<OrderDetails> findById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }
    public int todaySale() throws SQLException, ClassNotFoundException {
        String sql = """
        SELECT SUM(od.quantity) AS total_quantity
        FROM Order_Details od
        JOIN Orders o ON od.order_id = o.order_id
        WHERE o.order_date = ?
    """;
        ResultSet rs = SQLUtil.execute(sql, java.sql.Date.valueOf(LocalDate.now()));
        if (rs.next()) {
            return rs.getInt("total_quantity");
        }
        return 0;
    }
    public Map<LocalDate, Integer> getSalesForLast7Days() throws SQLException {
        Map<LocalDate, Integer> salesMap = new LinkedHashMap<>();

        // Step 1: Initialize the map with the last 7 days and default value 0
        LocalDate today = LocalDate.now();
        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            salesMap.put(date, 0); // Default zero sales
        }

        // Step 2: Query sales data from DB
        String sql = """
        SELECT o.order_date, SUM(od.quantity * od.unit_price) AS total
        FROM Orders o
        JOIN Order_Details od ON o.order_id = od.order_id
        WHERE o.order_date >= ?
        GROUP BY o.order_date
        ORDER BY o.order_date
    """;

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(today.minusDays(6)));
            ResultSet rs = ps.executeQuery();

            // Step 3: Replace zero values with actual sales totals if present
            while (rs.next()) {
                LocalDate date = rs.getDate("order_date").toLocalDate();
                int total = rs.getInt("total");
                salesMap.put(date, total);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver not found", e);
        }

        return salesMap;
    }


}
