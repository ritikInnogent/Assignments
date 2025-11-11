package com.example.demo.Dto;

import com.example.demo.Model.Address;
import com.example.demo.Model.OrderItem;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String userId = "Ritik";

    private Double totalAmount;

    private Double discountAmount = 0.0;

    private Double finalAmount;

    private String status = "PENDING";

    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    private String promoCodeUsed;

    private List<OrderItem> orderItems;

    private Address address;
}
