package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PendingOrder {
    private int pendingOrderId;
    private int orderId;
    private String customerName;
    private String productName;
    private int quantity;
    private String requestTime;
    private String status;

}
