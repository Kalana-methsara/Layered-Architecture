package com.lankaice.project.dto;
import com.lankaice.project.entity.Booking;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookingDto extends Booking {
    private int bookingId;
    private String customerId;
    private String productId;
    private Date requestDate;
    private String requestTime;
    private int quantity;
    private String status;

    public BookingDto(String customerId, String productId, String selectedDate, String selectedTime, int qty, String status) {
        this.customerId = customerId;
        this.productId = productId;
        this.requestDate = Date.valueOf(selectedDate);
        this.requestTime = selectedTime;
        this.quantity = qty;
        this.status = status;
    }


}
