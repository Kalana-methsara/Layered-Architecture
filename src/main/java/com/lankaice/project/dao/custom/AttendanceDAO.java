package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.Attendance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AttendanceDAO extends CrudDAO<Attendance> {

    boolean updateAttendanceShiftAndTimes(String empId, LocalDate date, String oldShift, String newShift, LocalTime inTime, LocalTime outTime)
            throws SQLException, ClassNotFoundException;

    boolean deleteAttendance(String employeeId, LocalDate date, String shift)
            throws SQLException, ClassNotFoundException;

    boolean markAttendance(Attendance attendance)
            throws SQLException, ClassNotFoundException;

    Attendance getAttendance(String empId, LocalDate date, String shift)
            throws SQLException, ClassNotFoundException;

    boolean updateOutTime(String empId, LocalDate date, String shift, LocalTime outTime)
            throws SQLException, ClassNotFoundException;

    List<Attendance> getAttendanceByDate(LocalDate date)
            throws SQLException, ClassNotFoundException;
}
