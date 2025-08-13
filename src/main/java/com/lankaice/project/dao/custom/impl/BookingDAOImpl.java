package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.BookingDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDAOImpl implements BookingDAO {
    @Override
    public List<Booking> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Booking");
        List<Booking> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Booking(
                    rs.getInt("booking_id"),
                    rs.getString("customer_id"),
                    rs.getString("product_id"),
                    rs.getDate("request_date"),
                    rs.getString("request_time"),
                    rs.getInt("quantity"),
                    rs.getString("status")
            ));
        }
        return list;
    }


    @Override
    public boolean save(Booking booking) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Booking (customer_id, product_id, request_date, request_time, quantity, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql,
                booking.getCustomerId(),
                booking.getProductId(),
                booking.getRequestDate(),
                booking.getRequestTime(),
                booking.getQuantity(),
                booking.getStatus()
        );    }

    @Override
    public boolean update(Booking booking) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Booking SET product_id = ?, request_time = ?, quantity = ?, status = ? " +
                "WHERE customer_id = ? AND request_date = ?";

        return SQLUtil.execute(sql,
                booking.getProductId(),      // 1
                booking.getRequestTime(),    // 2
                booking.getQuantity(),       // 3
                booking.getStatus(),         // 4
                booking.getCustomerId(),     // 5
                booking.getRequestDate()     // 6
        );    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<Booking> findById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Booking WHERE customer_id = ?", id);
        if (rs.next()) {
            return Optional.of(new Booking(
                    rs.getInt("booking_id"),
                    rs.getString("customer_id"),
                    rs.getString("product_id"),
                    rs.getDate("request_date"),
                    rs.getString("request_time"),
                    rs.getInt("quantity"),
                    rs.getString("status")
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteBooking(String customerId, String date) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Booking WHERE customer_id = ? AND request_date = ?";
        return SQLUtil.execute(sql, customerId, date);
    }

    @Override
    public Booking getBookingByCustomerIdAndDate(String customerId, String date, String quantity) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Booking WHERE customer_id = ? AND request_date = ? AND quantity = ?";
        ResultSet rs = SQLUtil.execute(sql, customerId, date, quantity);
        if (rs.next()) {
           return new Booking(
                    rs.getInt("booking_id"),
                    rs.getString("customer_id"),
                    rs.getString("product_id"),
                    rs.getDate("request_date"),
                    rs.getString("request_time"),
                    rs.getInt("quantity"),
                    rs.getString("status")
            );
        }
        return null;
    }

    @Override
    public boolean isDuplicate(String customerId, String date) throws SQLException, ClassNotFoundException {
        String sql = "SELECT 1 FROM Booking WHERE customer_id = ? AND request_date = ?";
        ResultSet rs = SQLUtil.execute(sql, customerId, date);
        return rs.next();
    }

    @Override
    public String getCustomerNameById(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT name FROM Customer WHERE customer_id = ?", customerId);
        if (rs.next()) {
            return rs.getString("name");
        }
        return "Unknown";
    }
}
