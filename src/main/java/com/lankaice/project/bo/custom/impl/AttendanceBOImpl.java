package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.AttendanceBO;
import com.lankaice.project.bo.exception.DuplicateException;
import com.lankaice.project.bo.util.EntityDTOConverter;
import com.lankaice.project.dao.custom.AttendanceDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.AttendanceDto;
import com.lankaice.project.entity.Attendance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttendanceBOImpl implements AttendanceBO {

    private final AttendanceDAO attendanceDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.ATTENDANCE);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public void saveAttendance(AttendanceDto dto) throws DuplicateException, Exception {
        Optional<Attendance> optionalAttendance = attendanceDAO.findById(dto.getEmployeeId());
        if (optionalAttendance.isPresent()) {
            throw new DuplicateException("Duplicate attendance for employee id: " + dto.getEmployeeId());
        }

        Attendance attendance = converter.getAttendance(dto);
        attendanceDAO.save(attendance);
    }

    @Override
    public List<AttendanceDto> getAllAttendance() throws SQLException, ClassNotFoundException {
        List<Attendance> attendanceList = attendanceDAO.getAll();
        List<AttendanceDto> dtoList = new ArrayList<>();
        for (Attendance attendance : attendanceList) {
            dtoList.add(converter.getAttendanceDto(attendance));
        }
        return dtoList;
    }

    @Override
    public boolean updateAttendanceShiftAndTimes(String empId, LocalDate date, String oldShift, String newShift, LocalTime inTime, LocalTime outTime) throws SQLException, ClassNotFoundException {
        return attendanceDAO.updateAttendanceShiftAndTimes(empId, date, oldShift, newShift, inTime, outTime);
    }

    @Override
    public boolean deleteAttendance(String employeeId, LocalDate date, String shift) throws SQLException, ClassNotFoundException {
        return attendanceDAO.deleteAttendance(employeeId, date, shift);
    }

    @Override
    public AttendanceDto getAttendance(String empId, LocalDate date, String shift) throws SQLException, ClassNotFoundException {
        Attendance attendance = attendanceDAO.getAttendance(empId, date, shift);
        if (attendance != null) {
            return converter.getAttendanceDto(attendance);
        }
        return null;
    }

    @Override
    public boolean updateOutTime(String empId, LocalDate date, String shift, LocalTime outTime) throws SQLException, ClassNotFoundException {
        return attendanceDAO.updateOutTime(empId, date, shift, outTime);
    }

    @Override
    public List<AttendanceDto> getAttendanceByDate(LocalDate date) throws SQLException, ClassNotFoundException {
        List<Attendance> attendanceList = attendanceDAO.getAttendanceByDate(date);
        List<AttendanceDto> dtoList = new ArrayList<>();
        for (Attendance attendance : attendanceList) {
            dtoList.add(converter.getAttendanceDto(attendance));
        }
        return dtoList;
    }

    @Override
    public boolean markAttendance(AttendanceDto dto) {
        return false;
    }
}
