package com.lankaice.project.bo.util;

import com.lankaice.project.dto.AttendanceDto;
import com.lankaice.project.dto.BookingDto;
import com.lankaice.project.dto.CustomerDto;
import com.lankaice.project.dto.EmployeeDto;
import com.lankaice.project.entity.Attendance;
import com.lankaice.project.entity.Booking;
import com.lankaice.project.entity.Customer;
import com.lankaice.project.entity.Employee;

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

    // Convert Entity -> DTO
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
}
