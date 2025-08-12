package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.BookingBO;
import com.lankaice.project.bo.exception.DuplicateException;
import com.lankaice.project.bo.exception.InUseException;
import com.lankaice.project.bo.exception.NotFoundException;
import com.lankaice.project.bo.util.EntityDTOConverter;
import com.lankaice.project.dao.custom.BookingDAO;
import com.lankaice.project.dao.custom.OrdersDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.BookingDto;
import com.lankaice.project.entity.Booking;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BookingBOImpl implements BookingBO {

    private final BookingDAO bookingDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.BOOKING);
    private final OrdersDAO ordersDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.ORDERS);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<BookingDto> getAllBookings() throws SQLException, ClassNotFoundException {
        List<Booking> bookings = bookingDAO.getAll();
        List<BookingDto> bookingDtos = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDtos.add(converter.getBookingDto(booking));
        }
        return bookingDtos;
    }

    @Override
    public void saveBooking(BookingDto dto) throws DuplicateException, Exception {
        // Check for duplicate booking
        if (bookingDAO.isDuplicate(dto.getCustomerId(), dto.getRequestDate().toString())) {
            throw new DuplicateException("Duplicate booking for this customer and date");
        }

        Booking booking = converter.getBooking(dto);
        bookingDAO.save(booking);
    }

    @Override
    public void updateBooking(BookingDto dto) throws SQLException, ClassNotFoundException {
        Optional<Booking> optionalBooking = bookingDAO.findById(dto.getCustomerId());
        if (optionalBooking.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }

        Booking booking = converter.getBooking(dto);
        bookingDAO.update(booking);
    }

    @Override
    public void deleteBooking(String customerId, String date) throws InUseException, Exception {
        Optional<Booking> optionalBooking = bookingDAO.findById(customerId);
        if (optionalBooking.isEmpty()) {
            throw new NotFoundException("Booking not found..!");
        }

        // If this booking has related orders, prevent deletion
        if (ordersDAO.existOrdersByCustomerId(customerId)) {
            throw new InUseException("Booking has linked orders");
        }

        bookingDAO.deleteBooking(customerId, date);
    }

    @Override
    public BookingDto findBooking(String customerId) throws SQLException, ClassNotFoundException {
        Optional<Booking> booking = bookingDAO.findById(customerId);
        return booking.map(converter::getBookingDto).orElse(null);
    }

    @Override
    public BookingDto getBookingByCustomerIdAndDate(String customerId, String date, String quantity) throws SQLException, ClassNotFoundException {
        Booking booking = bookingDAO.getBookingByCustomerIdAndDate(customerId, date, quantity);
        return booking != null ? converter.getBookingDto(booking) : null;
    }

    @Override
    public boolean isDuplicateBooking(String customerId, String date) throws SQLException, ClassNotFoundException {
        return bookingDAO.isDuplicate(customerId, date);
    }

    @Override
    public String getCustomerNameById(String customerId) throws SQLException, ClassNotFoundException {
        return bookingDAO.getCustomerNameById(customerId);
    }
}
