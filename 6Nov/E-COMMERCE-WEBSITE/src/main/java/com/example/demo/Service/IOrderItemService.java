package com.example.demo.Service;

import com.example.demo.Dto.OrderItemRequest;
import com.example.demo.Dto.OrderItemResponse;
import com.example.demo.Model.OrderItem;
import java.util.List;

public interface IOrderItemService {

    OrderItemResponse addOrderItem(OrderItemRequest orderItemRequest);

    List<OrderItem> getOrderItems();

    OrderItemResponse getOrderItemById(Long id);

    OrderItemResponse updateOrderItem(Long id, OrderItemRequest orderItemRequest);

    String deleteOrderItem(Long id);
    }

