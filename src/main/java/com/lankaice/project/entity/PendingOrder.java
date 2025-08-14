package com.lankaice.project.entity;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PendingOrder {
    private int pendingOrderId;
    private int orderId;
    private String customerName;
    private String productName;
    private int quantity;
    private LocalDateTime requestTime;
    private String status;

}
