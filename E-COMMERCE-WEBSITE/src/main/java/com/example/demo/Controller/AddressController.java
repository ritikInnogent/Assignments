package com.example.demo.Controller;

import com.example.demo.Dto.AddressRequest;
import com.example.demo.Dto.AddressResponse;
import com.example.demo.Model.Address;
import com.example.demo.Service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController   //send data in json format
@RequestMapping("/AddressItem")    //parent root end point
public class AddressController {
    @Autowired
    private IAddressService service;  //Spring automatically create object without new keyword and assign into the variable

    @PostMapping("/insert")
    public ResponseEntity<AddressResponse> create(@RequestBody AddressRequest request){
        return new ResponseEntity<AddressResponse>(service.addAddress(request), HttpStatus.CREATED);
    }

    @GetMapping("/getAddress")
    public ResponseEntity<List<Address>> getAll(){
        return new ResponseEntity<List<Address>>(service.getAddresses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<AddressResponse>(service.getAddressById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressResponse> update(@PathVariable Long id, @RequestBody AddressRequest request) {
        return new ResponseEntity<AddressResponse>(service.updateAddress(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<String>(service.deleteAddress(id), HttpStatus.OK);
    }
}
