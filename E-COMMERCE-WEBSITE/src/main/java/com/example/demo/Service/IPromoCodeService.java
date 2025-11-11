package com.example.demo.Service;

import com.example.demo.Dto.PromoCodeRequest;
import com.example.demo.Dto.PromoCodeResponse;
import com.example.demo.Model.PromoCodes;

import java.util.List;

public interface IPromoCodeService {
    PromoCodeResponse addPromoCode(PromoCodeRequest PromoCodeRequest);

    List<PromoCodes> getPromoCodes();

    PromoCodeResponse getPromoCodeById(Long id);

    PromoCodeResponse updatePromoCode(Long id, PromoCodeRequest PromoCodeRequest);

    String deletePromoCode(Long id);
}
