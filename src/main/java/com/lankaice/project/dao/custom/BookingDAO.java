package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.dto.BookingDto;
import com.lankaice.project.entity.Booking;

import java.sql.SQLException;

public interface BookingDAO extends CrudDAO<Booking> {
     boolean deleteBooking(String customerId, String date) throws SQLException, ClassNotFoundException;

     Booking getBookingByCustomerIdAndDate(String customerId, String date, String quantity) throws SQLException, ClassNotFoundException;

     boolean isDuplicate(String customerId, String date) throws SQLException, ClassNotFoundException;

     String getCustomerNameById(String customerId) throws SQLException, ClassNotFoundException;


    }
