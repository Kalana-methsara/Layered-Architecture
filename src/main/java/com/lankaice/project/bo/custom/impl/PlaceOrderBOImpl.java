package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.PlaceOrderBO;
import com.lankaice.project.bo.custom.VehicleBO;
import com.lankaice.project.dao.custom.*;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.db.DBConnection;
import com.lankaice.project.dto.BookingDto;
import com.lankaice.project.dto.OrderDetailsDto;
import com.lankaice.project.dto.OrdersDto;
import com.lankaice.project.dto.PendingOrderDto;
import com.lankaice.project.entity.Booking;
import com.lankaice.project.entity.OrderDetails;
import com.lankaice.project.entity.Orders;
import com.lankaice.project.entity.PendingOrder;
import org.threeten.bp.LocalDateTime;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    private final OrdersDAO ordersDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.ORDERS);
    private final OrderDetailsDAO orderDetailsDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.ORDER_DETAIL);
    private final StockDAO stockDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.STOCK);
    private final PendingOrderDAO pendingOrderDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.PENDING_ORDERS);
    private final BookingDAO bookingDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.BOOKING);
    private final DeliveryDAO deliveryDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.DELIVERY);
    private final VehicleDAO vehicleDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.VEHICLE);
    private final CustomerDAO customerDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.CUSTOMER);
    private final ProductDAO productDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.PRODUCT);


    private VehicleBO vehicleBO; // If your VehicleBO is needed for vehicleId fetching

    @Override
    public boolean placeOrder(OrdersDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            // Save order
            Orders orders = new Orders(
                    dto.getOrderId(),
                    dto.getCustomerId(),
                    dto.getOrderDate(),
                    dto.getOrderTime(),
                    dto.getDescription(),
                    dto.getVehicle_number(),
                    dto.getTotalAmount()
            );

            if (!ordersDAO.save(orders)) {
                connection.rollback();
                return false;
            }

            // Save details, update stock, insert pending orders
            if (!saveDetailsStockAndPending(dto)) {
                connection.rollback();
                return false;
            }
            for (OrderDetailsDto detailsDto : dto.getCartList()) {
            // Update booking if exists
            if (bookingDAO.isDuplicate(dto.getCustomerId(), dto.getOrderDate())) {

                Booking booking = new BookingDto(
                        dto.getCustomerId(),
                        detailsDto.getProductId(),
                        dto.getOrderDate(),
                        LocalTime.now().toString(),
                        detailsDto.getQuantity(),
                        "Confirmed"
                        );
                if (!bookingDAO.update(booking)) {
                    connection.rollback();
                    return false;
                }
            }
        }
            // Add delivery & update vehicle
            if (dto.getVehicle_number() != null && !dto.getVehicle_number().isEmpty()) {
                String vehicleId = vehicleBO.getVehicleIdByNumber(dto.getVehicle_number());

                if (!deliveryDAO.save(dto.getOrderId(), dto.getOrderDate(),
                        dto.getOrderTime(), "Galle", "Pending", vehicleId)) {
                    connection.rollback();
                    return false;
                }

                if (!vehicleDAO.updates(vehicleId, "Inactive")) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private boolean saveDetailsStockAndPending(OrdersDto dto) throws SQLException, ClassNotFoundException {
        for (OrderDetailsDto detailsDto : dto.getCartList()) {

            // Save order details
            OrderDetails orderDetails = new OrderDetails(
                    dto.getOrderId(),
                    detailsDto.getProductId(),
                    detailsDto.getQuantity(),
                    detailsDto.getUnitPrice(),
                    detailsDto.getDiscount()
            );

            if (!orderDetailsDAO.save(orderDetails)) {
                return false;
            }

            // Reduce stock
            if (!stockDAO.reduceQty(detailsDto.getProductId(), detailsDto.getQuantity())) {
                return false;
            }
        }
        return true;
    }
}
