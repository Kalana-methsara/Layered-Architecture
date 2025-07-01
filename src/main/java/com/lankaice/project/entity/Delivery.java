package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Delivery{
    private int deliveryId;
    private int orderId;
    private String deliveryDate;
    private String deliveryTime;
    private String deliveryAddress;
    private String deliveryState;
    private int vehicleId;

}
