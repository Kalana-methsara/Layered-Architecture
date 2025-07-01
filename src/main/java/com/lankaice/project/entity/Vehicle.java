package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {
    private int vehicleId;
    private String vehicleNumber;
    private String status;
    private int capacity;
    private String model;


}
