package com.example.demo.Controller;

import com.example.demo.Dto.PromoCodeRequest;
import com.example.demo.Dto.PromoCodeResponse;
import com.example.demo.Model.PromoCodes;
import com.example.demo.Service.IPromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController   //send data in json format
@RequestMapping("/promocode")    //parent root end point
public class PromoCodeController {
    @Autowired        //dependency injection
    private IPromoCodeService service;  //Spring automatically create object without new keyword and assign into the variable

    @PostMapping("/insert")
    public ResponseEntity<PromoCodeResponse> create(@RequestBody PromoCodeRequest request){
        return new ResponseEntity<PromoCodeResponse>(service.addPromoCode(request), HttpStatus.CREATED);
    }

    @GetMapping("/getpromocode")
    public ResponseEntity<List<PromoCodes>> getAll(){
        return new ResponseEntity<List<PromoCodes>>(service.getPromoCodes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromoCodeResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<PromoCodeResponse>(service.getPromoCodeById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PromoCodeResponse> update(@PathVariable Long id, @RequestBody PromoCodeRequest request) {
        return new ResponseEntity<PromoCodeResponse>(service.updatePromoCode(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<String>(service.deletePromoCode(id), HttpStatus.OK);
    }
}
