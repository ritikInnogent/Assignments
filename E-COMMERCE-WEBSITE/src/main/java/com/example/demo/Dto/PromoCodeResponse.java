package com.example.demo.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoCodeResponse {

    private Integer id;
    private String code;
    private Double discountPercentage;
    private LocalDateTime validFrom;
    private LocalDateTime validTill;
    private Boolean isActive = true;
}
