package com.lankaice.project.entity;

import com.lankaice.project.dto.OrderPaymentDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    private int orderId;
    private String customerId;
    private String orderDate;
    private String orderTime;
    private String description;
    private String vehicle_number;
    private double totalAmount;


}
