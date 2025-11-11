package com.example.demo.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;

    private String userId = "Ritik";

    private Double totalAmount;

    private Double discountAmount = 0.0;

    private Double finalAmount;

    private String status = "PENDING";

    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    private String promoCodeUsed;


}
