package com.example.demo.Service;

import com.example.demo.Dto.OrderRequest;
import com.example.demo.Dto.OrderResponse;

import com.example.demo.Model.Order;


import java.util.List;

public interface IOrderService {
    OrderResponse addOrder(OrderRequest orderRequest);

    List<Order> getOrders();

    OrderResponse getOrderById(Long id);

    OrderResponse updateOrder(Long id, OrderRequest orderRequest);

    String deleteOrder(Long id);
}
