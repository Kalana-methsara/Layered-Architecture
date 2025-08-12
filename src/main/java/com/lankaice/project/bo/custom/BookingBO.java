package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.bo.exception.DuplicateException;
import com.lankaice.project.bo.exception.InUseException;
import com.lankaice.project.dto.BookingDto;

import java.sql.SQLException;
import java.util.List;

public interface BookingBO extends SuperBO {

    List<BookingDto> getAllBookings() throws SQLException, ClassNotFoundException;

    void saveBooking(BookingDto dto) throws DuplicateException, Exception;

    void updateBooking(BookingDto dto) throws SQLException, ClassNotFoundException;

    void deleteBooking(String customerId, String date) throws InUseException, Exception;

    BookingDto findBooking(String customerId) throws SQLException, ClassNotFoundException;

    BookingDto getBookingByCustomerIdAndDate(String customerId, String date, String quantity)
            throws SQLException, ClassNotFoundException;

    boolean isDuplicateBooking(String customerId, String date) throws SQLException, ClassNotFoundException;

    String getCustomerNameById(String customerId) throws SQLException, ClassNotFoundException;
}
