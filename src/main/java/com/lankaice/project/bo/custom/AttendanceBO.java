package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.bo.exception.DuplicateException;
import com.lankaice.project.dto.AttendanceDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AttendanceBO extends SuperBO {

    void saveAttendance(AttendanceDto dto) throws DuplicateException, Exception;

    List<AttendanceDto> getAllAttendance() throws SQLException, ClassNotFoundException;

    boolean updateAttendanceShiftAndTimes(String empId, LocalDate date, String oldShift, String newShift, LocalTime inTime, LocalTime outTime) throws SQLException, ClassNotFoundException;

    boolean deleteAttendance(String employeeId, LocalDate date, String shift) throws SQLException, ClassNotFoundException;

    AttendanceDto getAttendance(String empId, LocalDate date, String shift) throws SQLException, ClassNotFoundException;

    boolean updateOutTime(String empId, LocalDate date, String shift, LocalTime outTime) throws SQLException, ClassNotFoundException;

    List<AttendanceDto> getAttendanceByDate(LocalDate date) throws SQLException, ClassNotFoundException;

    boolean markAttendance(AttendanceDto dto) throws SQLException, ClassNotFoundException;
}
