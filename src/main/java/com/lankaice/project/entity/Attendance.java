package com.lankaice.project.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance{
    private int attendanceId;
    private String employeeId;
    private LocalDate date;
    private String shift;
    private String status;
    private LocalTime inTime;
    private LocalTime outTime;
}
