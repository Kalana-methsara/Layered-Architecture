package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.OrdersDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.OrdersDto;
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
        ArrayList<Orders> orders = new ArrayList<>();

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
            orders.add(order);
        }
        return orders;
    }

    @Override
    public boolean save(Orders orders) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders orders) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<Orders> findById(String id) throws SQLException, ClassNotFoundException {
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

      /*  String productId = ProductDAOImpl.findIdByName(productName);
        String sql = "UPDATE PendingOrder SET status = ? WHERE order_id = ? AND product_id = ?";
        return SQLUtil.execute(sql, newStatus, orderId, productId);*/
        return false;
    }
    @Override
    public boolean existOrdersByCustomerId(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Orders WHERE customer_id = ?", customerId);
        return resultSet.next();
    }
}
