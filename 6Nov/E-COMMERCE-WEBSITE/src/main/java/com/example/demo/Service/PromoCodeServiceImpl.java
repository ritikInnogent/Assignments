package com.example.demo.Service;

import com.example.demo.Dto.PromoCodeRequest;
import com.example.demo.Dto.PromoCodeResponse;
import com.example.demo.Model.PromoCodes;
import com.example.demo.Repository.PromoCodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PromoCodeServiceImpl implements IPromoCodeService {

    @Autowired
    private PromoCodeRepo repo;

    @Override
    public PromoCodeResponse addPromoCode(PromoCodeRequest PromoCodeRequest) {
        PromoCodes promocode = new PromoCodes();
        promocode.setCode(PromoCodeRequest.getCode());
        promocode.setValidFrom(PromoCodeRequest.getValidFrom());
        promocode.setValidTill(PromoCodeRequest.getValidTill());
        promocode.setDiscountPercentage(PromoCodeRequest.getDiscountPercentage());
        promocode.setIsActive(PromoCodeRequest.getIsActive());

        repo.save(promocode);

        PromoCodeResponse  PromoCodeResponse = new PromoCodeResponse();
        PromoCodeResponse.setValidTill(promocode.getValidTill());
        PromoCodeResponse.setDiscountPercentage(promocode.getDiscountPercentage());
        PromoCodeResponse.setValidFrom(promocode.getValidFrom());
        PromoCodeResponse.setIsActive(promocode.getIsActive());
        PromoCodeResponse.setCode(promocode.getCode());
        return PromoCodeResponse;
    }



    @Override
    public PromoCodeResponse updatePromoCode(Long id, PromoCodeRequest PromoCodeRequest) {
        PromoCodes promocode = repo.findById(id).orElseThrow(()-> new RuntimeException("PromoCode not exist"));

        promocode.setCode(PromoCodeRequest.getCode());
        promocode.setValidFrom(PromoCodeRequest.getValidFrom());
        promocode.setValidTill(PromoCodeRequest.getValidTill());
        promocode.setDiscountPercentage(PromoCodeRequest.getDiscountPercentage());
        promocode.setIsActive(PromoCodeRequest.getIsActive());
        repo.save(promocode);

        PromoCodeResponse  PromoCodeResponse = new PromoCodeResponse();
        PromoCodeResponse.setValidTill(promocode.getValidTill());
        PromoCodeResponse.setDiscountPercentage(promocode.getDiscountPercentage());
        PromoCodeResponse.setValidFrom(promocode.getValidFrom());
        PromoCodeResponse.setIsActive(promocode.getIsActive());
        PromoCodeResponse.setCode(promocode.getCode());
        return PromoCodeResponse;
    }



    @Override
    public String deletePromoCode(Long id) {
        PromoCodes PromoCodes = repo.findById(id).orElseThrow(() -> new RuntimeException("PromoCode not exist"));
        repo.delete(PromoCodes);
        return "PromoCode deleted successfully!";
    }

    @Override
    public List<PromoCodes> getPromoCodes() {
        return repo.findAll();
    }

    @Override
    public PromoCodeResponse getPromoCodeById(Long id) {
        PromoCodes promocode = repo.findById(id).orElseThrow(()-> new RuntimeException("PromoCode not exist"));

        PromoCodeResponse response = new PromoCodeResponse();
        response.setCode(promocode.getCode());
        response.setDiscountPercentage(promocode.getDiscountPercentage());
        response.setValidTill(promocode.getValidTill());
        response.setValidFrom(promocode.getValidFrom());
        response.setIsActive(promocode.getIsActive());
        return response;
    }
}
