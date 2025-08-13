package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.AttendanceDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.Attendance;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public List<Attendance> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }


    @Override
    public boolean save(Attendance attendance) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Attendance (employee_id,name, date, shift, status, in_time, out_time) VALUES (?, ?,?, ?, ?, ?, ?)";

        return SQLUtil.execute(
                sql,
                attendance.getEmployeeId(),
                attendance.getName(),
                Date.valueOf(attendance.getDate()),
                attendance.getShift(),
                attendance.getStatus(),
                attendance.getInTime() != null ? Time.valueOf(attendance.getInTime()) : null,
                attendance.getOutTime() != null ? Time.valueOf(attendance.getOutTime()) : null
        );    }

    @Override
    public boolean update(Attendance attendance) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<Attendance> findById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Attendance WHERE employee_id = ?", id);
        if (resultSet.next()) {
            return Optional.of(new Attendance(
                    resultSet.getInt("attendance_id"),
                    resultSet.getString("employee_id"),
                    resultSet.getString("name"),
                    resultSet.getDate("date").toLocalDate(),
                    resultSet.getString("shift"),
                    resultSet.getString("status"),
                    resultSet.getTime("in_time") != null ? resultSet.getTime("in_time").toLocalTime() : null,
                    resultSet.getTime("out_time") != null ? resultSet.getTime("out_time").toLocalTime() : null
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean updateAttendanceShiftAndTimes(String empId, LocalDate date, String oldShift, String newShift, LocalTime inTime, LocalTime outTime) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Attendance SET in_time = ?, out_time = ?, shift = ? WHERE employee_id = ? AND date = ? AND shift = ?";

        return SQLUtil.execute(
                sql,
                inTime != null ? Time.valueOf(inTime) : null,
                outTime != null ? Time.valueOf(outTime) : null,
                newShift,
                empId,
                Date.valueOf(date),
                oldShift
        );    }

    @Override
    public boolean deleteAttendance(String employeeId, LocalDate date, String shift) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Attendance WHERE employee_id = ? AND date = ? AND shift = ?";
        return SQLUtil.execute(sql, employeeId, Date.valueOf(date), shift);
    }

    @Override
    public boolean markAttendance(Attendance attendance) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Attendance (employee_id, name,date, shift, status, in_time, out_time) " +
                "VALUES (?,?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE status = VALUES(status), in_time = VALUES(in_time), out_time = VALUES(out_time)";

        return SQLUtil.<Boolean>execute(
                sql,
                attendance.getEmployeeId(),
                attendance.getName(),
                Date.valueOf(attendance.getDate()),
                attendance.getShift(),
                attendance.getStatus(),
                attendance.getInTime() != null ? Time.valueOf(attendance.getInTime()) : null,
                attendance.getOutTime() != null ? Time.valueOf(attendance.getOutTime()) : null
        );
    }

    @Override
    public Attendance getAttendance(String empId, LocalDate date, String shift) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Attendance WHERE employee_id = ? AND date = ? AND shift = ?";
        ResultSet rs = SQLUtil.execute(sql, empId, Date.valueOf(date), shift);

        if (rs.next()) {
            return new Attendance(
                    rs.getInt("attendance_id"),
                    rs.getString("employee_id"),
                    rs.getString("name"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("shift"),
                    rs.getString("status"),
                    rs.getTime("in_time") != null ? rs.getTime("in_time").toLocalTime() : null,
                    rs.getTime("out_time") != null ? rs.getTime("out_time").toLocalTime() : null
            );
        }
        return null;    }

    @Override
    public boolean updateOutTime(String empId, LocalDate date, String shift, LocalTime outTime) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Attendance SET out_time = ? WHERE employee_id = ? AND date = ? AND shift = ?";
        return SQLUtil.<Boolean>execute(
                sql,
                Time.valueOf(outTime),
                empId,
                Date.valueOf(date),
                shift
        );    }

    @Override
    public List<Attendance> getAttendanceByDate(LocalDate dateString) throws SQLException, ClassNotFoundException {
        Date date = Date.valueOf(dateString);
        String sql = "SELECT a.*, e.name FROM Attendance a " +
                "JOIN Employee e ON a.employee_id = e.employee_id " +
                "WHERE a.date = ?";

        ResultSet rs = SQLUtil.execute(sql, date);
        List<Attendance> list = new ArrayList<>();

        while (rs.next()) {
            Attendance dto = new Attendance();
            dto.setEmployeeId(rs.getString("employee_id"));
            dto.setName(rs.getString("name"));
            dto.setDate(rs.getDate("date").toLocalDate());
            dto.setShift(rs.getString("shift"));
            dto.setStatus(rs.getString("status"));
            dto.setInTime(rs.getTime("in_time") != null ? rs.getTime("in_time").toLocalTime() : null);
            dto.setOutTime(rs.getTime("out_time") != null ? rs.getTime("out_time").toLocalTime() : null);
            list.add(dto);
        }

        return list;
    }

}
