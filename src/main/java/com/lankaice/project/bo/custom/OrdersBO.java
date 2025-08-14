package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.OrdersDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrdersBO extends SuperBO {
    List<OrdersDto> getAllOrders() throws SQLException, ClassNotFoundException;
    boolean saveOrder(OrdersDto dto) throws SQLException, ClassNotFoundException;
    boolean updateOrder(OrdersDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;
    Optional<OrdersDto> findOrderById(String id) throws SQLException, ClassNotFoundException;
    int getLastOrderId() throws SQLException, ClassNotFoundException;
    boolean updateOrderStatus(int orderId, String productName, String newStatus) throws SQLException, ClassNotFoundException;
    boolean existOrdersByCustomerId(String customerId) throws SQLException, ClassNotFoundException;
}
