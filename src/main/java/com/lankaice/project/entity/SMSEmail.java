package com.lankaice.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SMSEmail{
    private int notificationId;
    private int customerId;
    private int orderId;
    private String type;
    private String date;
}
