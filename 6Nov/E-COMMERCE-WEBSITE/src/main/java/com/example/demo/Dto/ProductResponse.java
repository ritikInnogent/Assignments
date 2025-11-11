package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {


    private Integer id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private Double rate;

    private Integer count;
}
