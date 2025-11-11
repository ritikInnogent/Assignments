package com.example.demo.Controller;

import com.example.demo.Dto.OrderRequest;
import com.example.demo.Dto.OrderResponse;
import com.example.demo.Model.Order;
import com.example.demo.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController   //send data in json format
@RequestMapping("/order")    //parent root end point

public class OrderController {

    @Autowired
    private IOrderService service;  //Spring automatically create object without new keyword and assign into the variable

    @PostMapping("/insert")
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest request){
        return new ResponseEntity<OrderResponse>(service.addOrder(request), HttpStatus.CREATED);
    }

    @GetMapping("/getOrder")
    public ResponseEntity<List<Order>> getAll(){
        return new ResponseEntity<List<Order>>(service.getOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<OrderResponse>(service.getOrderById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable Long id, @RequestBody OrderRequest request) {
        return new ResponseEntity<OrderResponse>(service.updateOrder(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<String>(service.deleteOrder(id), HttpStatus.OK);
    }
}
