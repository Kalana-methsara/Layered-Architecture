package com.lankaice.project.dto;

import com.google.type.DateTime;
import com.lankaice.project.entity.PendingOrder;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PendingOrderDto {
    private int pendingOrderId;
    private int orderId;
    private String customerName;
    private String productName;
    private int quantity;
    private String requestTime;
    private String status;

    public PendingOrderDto(int orderId, String customerName, String productName, int quantity,String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.status = status;
    }

}
