package com.lankaice.project.entity;

import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transport {
    private String transportId;
    private String productId;
    private String vehicleNumber;
    private String transportDate;
    private LocalTime deliveryBeginTime;
    private LocalTime deliveryEndTime;
    private int quantity;
    private String location;
    private String status;

}
