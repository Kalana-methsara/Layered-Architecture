package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.PlaceOrderBO;
import com.lankaice.project.dao.DAOFactory;
import com.lankaice.project.dao.custom.OrderDetailsDAO;
import com.lankaice.project.dao.custom.OrdersDAO;
import com.lankaice.project.dao.custom.StockDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.db.DBConnection;
import com.lankaice.project.dto.OrderDetailsDto;
import com.lankaice.project.dto.OrdersDto;
import com.lankaice.project.entity.OrderDetails;
import com.lankaice.project.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    private OrdersDAO ordersDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.ORDERS);
    private OrderDetailsDAO orderDetailsDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.ORDER_DETAIL);
    private StockDAO stockDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.STOCK);

    @Override
    public boolean placeOrder(OrdersDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            Orders orders = new Orders();
            orders.setOrderId(dto.getOrderId());
            orders.setCustomerId(dto.getCustomerId());
            orders.setOrderDate(dto.getOrderDate());
            orders.setOrderTime(dto.getOrderTime());
            orders.setVehicle_number(dto.getVehicle_number());
            orders.setTotalAmount(dto.getTotalAmount());

            boolean isOrderSaved = ordersDAO.save(orders);

            if (isOrderSaved) {
                boolean isSuccess = saveDetailsAndUpdateStock(dto.getCartList());
                if (isSuccess) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;

        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);

        }
    }

    private boolean saveDetailsAndUpdateStock(List<OrderDetailsDto> orderDetailsDtoList) throws SQLException, ClassNotFoundException {
        for (OrderDetailsDto detailsDto : orderDetailsDtoList) {

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderId(detailsDto.getOrderId());
            orderDetails.setProductId(detailsDto.getProductId());
            orderDetails.setQuantity(detailsDto.getQuantity());
            orderDetails.setUnitPrice(detailsDto.getUnitPrice());
            orderDetails.setDiscount(detailsDto.getDiscount());

            if (!orderDetailsDAO.save(orderDetails)) {
                return false;
            }

            boolean isStockUpdated = stockDAO.reduceQty(detailsDto.getProductId(), detailsDto.getQuantity());
            if (!isStockUpdated) {
                return false;
            }
        }
        return true;
    }
}
