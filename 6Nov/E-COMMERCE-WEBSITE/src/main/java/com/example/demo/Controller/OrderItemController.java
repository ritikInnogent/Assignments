package com.example.demo.Controller;

import com.example.demo.Dto.OrderItemRequest;
import com.example.demo.Dto.OrderItemResponse;
import com.example.demo.Model.OrderItem;
import com.example.demo.Service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController   //send data in json format
@RequestMapping("/OrderItem")    //parent root end point

public class OrderItemController {

    @Autowired
    private IOrderItemService service;  //Spring automatically create object without new keyword and assign into the variable

    @PostMapping("/insert")
    public ResponseEntity<OrderItemResponse> create(@RequestBody OrderItemRequest request){
        return new ResponseEntity<OrderItemResponse>(service.addOrderItem(request), HttpStatus.CREATED);
    }

    @GetMapping("/getOrderItem")
    public ResponseEntity<List<OrderItem>> getAll(){
        return new ResponseEntity<List<OrderItem>>(service.getOrderItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<OrderItemResponse>(service.getOrderItemById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderItemResponse> update(@PathVariable Long id, @RequestBody OrderItemRequest request) {
        return new ResponseEntity<OrderItemResponse>(service.updateOrderItem(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<String>(service.deleteOrderItem(id), HttpStatus.OK);
    }
}
