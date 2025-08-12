package com.lankaice.project.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AttendanceDto {
    private int attendanceId;
    private String employeeId;
    private String name;
    private LocalDate date;
    private String shift;
    private String status;
    private LocalTime inTime;
    private LocalTime outTime;
    private String workingHours;

    public AttendanceDto( String employeeId,String name, LocalDate date, String shift, String status, LocalTime inTime, LocalTime outTime) {
        this.employeeId = employeeId;
        this.name = name;
        this.date = date;
        this.shift = shift;
        this.status = status;
        this.inTime = inTime;
        this.outTime = outTime;
    }


}
