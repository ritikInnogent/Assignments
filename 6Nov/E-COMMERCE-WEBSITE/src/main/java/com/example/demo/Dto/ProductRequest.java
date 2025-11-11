package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Also directly exposing our entity is quite risky
// in DTO we can control which type of data we have to send in frontend
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private Integer id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private Double rate;

    private Integer count;
}
