package com.example.demo.Controller;
import com.example.demo.Dto.ProductRequest;
import com.example.demo.Dto.ProductResponse;
import com.example.demo.Model.Products;
import com.example.demo.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController   //send data in json format
@RequestMapping("/products")    //parent root end point
public class ProductController {

   @Autowired
   private IProductService service;  //Spring automatically create object without new keyword and assign into the variable

    @PostMapping("/insert")
    public ResponseEntity<List<ProductResponse>> create(@RequestBody List<ProductRequest> requests) {
        System.out.println(requests);
        return new ResponseEntity<List<ProductResponse>>(service.addProduct(requests),HttpStatus.CREATED);
    }

    // Sabhi products ko GET karne ka simple endpoint
    @GetMapping("/getProducts")
    public ResponseEntity<List<Products>> getAll(){
        return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<ProductResponse>(service.getProductById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> update (@PathVariable Long id, @RequestBody ProductRequest request) {
        return new ResponseEntity<ProductResponse>(service.updateProduct(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<String>(service.deleteProduct(id), HttpStatus.OK);
    }
}
