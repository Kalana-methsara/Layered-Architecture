package com.lankaice.project.bo.util;

import com.lankaice.project.dto.*;
import com.lankaice.project.dto.tm.CartItemTM;
import com.lankaice.project.entity.*;

public class EntityDTOConverter {

    public Customer getCustomer(CustomerDto dto) {
        if (dto == null) return null;
        return new Customer(
                dto.getCustomerId(),
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getContact(),
                dto.getAddress(),
                dto.getDescription()
        );
    }

    public CustomerDto getCustomerDto(Customer entity) {
        if (entity == null) return null;
        return new CustomerDto(
                entity.getCustomerId(),
                entity.getName(),
                entity.getNic(),
                entity.getEmail(),
                entity.getContact(),
                entity.getAddress(),
                entity.getDescription()
        );
    }

    public Attendance getAttendance(AttendanceDto dto) {
        if (dto == null) return null;
        return new Attendance(
                dto.getAttendanceId(),
                dto.getEmployeeId(),
                dto.getName(),
                dto.getDate(),
                dto.getShift(),
                dto.getStatus(),
                dto.getInTime(),
                dto.getOutTime()
        );
    }

    public AttendanceDto getAttendanceDto(Attendance attendance) {
        if (attendance == null) return null;
        return new AttendanceDto(
                attendance.getEmployeeId(),
                attendance.getName(),
                attendance.getDate(),
                attendance.getShift(),
                attendance.getStatus(),
                attendance.getInTime(),
                attendance.getOutTime()
        );
    }

    public BookingDto getBookingDto(Booking booking) {
        if (booking == null) return null;
        return new BookingDto(
                booking.getBookingId(),
                booking.getCustomerId(),
                booking.getProductId(),
                booking.getRequestDate(),
                booking.getRequestTime(),
                booking.getQuantity(),
                booking.getStatus()
        );
    }

    public Booking getBooking(BookingDto dto) {
        if (dto == null) return null;
        return new Booking(
                dto.getBookingId(),
                dto.getCustomerId(),
                dto.getProductId(),
                dto.getRequestDate(),
                dto.getRequestTime(),
                dto.getQuantity(),
                dto.getStatus()
        );
    }

    public EmployeeDto getEmployeeDto(Employee employee) {
        if (employee == null) return null;
        return new EmployeeDto(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getNic(),
                employee.getContact(),
                employee.getEmail(),
                employee.getJobRole(),
                employee.getAddress(),
                employee.getJoinDate(),
                employee.getDateOfBirth(),
                employee.getGender(),
                employee.getBankAccountNo(),
                employee.getBankBranch(),
                employee.getLicenseNumber()
        );
    }

    public Employee getEmployee(EmployeeDto dto) {
        if (dto == null) return null;
        return new Employee(
                dto.getEmployeeId(),
                dto.getName(),
                dto.getNic(),
                dto.getContact(),
                dto.getEmail(),
                dto.getJobRole(),
                dto.getAddress(),
                dto.getJoinDate(),
                dto.getDateOfBirth(),
                dto.getGender(),
                dto.getBankAccountNo(),
                dto.getBankBranch(),
                dto.getLicenseNumber()
        );
    }

    public InventoryCart getInventoryCart(CartItemTM dto) {
        if (dto == null) return null;
        return new InventoryCart(
                dto.getCartId(),
                dto.getSupplierId(),
                dto.getMaterialId(),
                dto.getName(),
                dto.getUnitType(),
                dto.getUnitPrice(),
                dto.getQuantity(),
                dto.getTotal()
        );
    }

    public CartItemTM getInventoryCartDto(InventoryCart entity) {
        if (entity == null) return null;
        return new CartItemTM(
                entity.getCartId(),
                entity.getSupplierId(),
                entity.getMaterialId(),
                entity.getName(),
                entity.getUnitType(),
                entity.getUnitPrice(),
                entity.getQuantity(),
                entity.getTotal()
        );
    }

    public RawMaterialsDto getRawMaterialsDto(RawMaterials entity) {
        return new RawMaterialsDto(
                entity.getMaterialId(),
                entity.getSupplierId(),
                entity.getName(),
                entity.getUnitType(),
                entity.getUnitCost(),
                entity.getLastUpdate(),
                entity.getQuantityAvailable()
        );
    }


    public Transport getTransport(TransportDto dto) {
        if (dto == null) return null;
        return new Transport(
                dto.getTransportId(),
                dto.getProductId(),
                dto.getVehicleNumber(),
                dto.getTransportDate(),
                dto.getDeliveryBeginTime(),
                dto.getDeliveryEndTime(),
                dto.getQuantity(),
                dto.getLocation(),
                dto.getStatus()
        );
    }

    public TransportDto getTransportDto(Transport entity) {
        if (entity == null) return null;
        return new TransportDto(
                entity.getTransportId(),
                entity.getProductId(),
                entity.getVehicleNumber(),
                entity.getTransportDate(),
                entity.getDeliveryBeginTime(),
                entity.getDeliveryEndTime(),
                entity.getQuantity(),
                entity.getLocation(),
                entity.getStatus()
        );
    }
    public Stock getStock(StockDto dto) {
        if (dto == null) return null;
        return new Stock(
                dto.getStockId(),
                dto.getProductId(),
                dto.getProductName(),
                dto.getQty(),
                dto.getDate(),
                dto.getTime()
        );
    }

    public StockDto getStockDto(Stock entity) {
        if (entity == null) return null;
        return new StockDto(
                entity.getStockId(),
                entity.getProductId(),
                entity.getProductName(),
                entity.getQty(),
                entity.getDate(),
                entity.getTime()
        );
    }

}
