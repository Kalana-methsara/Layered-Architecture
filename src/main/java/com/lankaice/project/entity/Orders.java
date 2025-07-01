package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders{
    private int orderId;
    private String customerId;
    private String orderDate;
    private String orderTime;
    private String description;
    private String vehicle_number;
    private double totalAmount;


}
