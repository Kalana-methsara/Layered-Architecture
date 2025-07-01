package com.lankaice.project.entity;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking{
    private int bookingId;
    private String customerId;
    private String productId;
    private Date requestDate;
    private String requestTime;
    private int quantity;
    private String status;


}
