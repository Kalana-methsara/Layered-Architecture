package com.lankaice.project.bo.util;

import com.lankaice.project.dto.AttendanceDto;
import com.lankaice.project.dto.CustomerDto;
import com.lankaice.project.entity.Attendance;
import com.lankaice.project.entity.Customer;

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
}
