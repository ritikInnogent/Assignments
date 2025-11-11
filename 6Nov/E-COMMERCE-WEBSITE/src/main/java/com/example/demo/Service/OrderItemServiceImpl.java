package com.example.demo.Service;

import com.example.demo.Dto.OrderItemRequest;
import com.example.demo.Dto.OrderItemResponse;
import com.example.demo.Model.OrderItem;
import com.example.demo.Repository.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements IOrderItemService {

    @Autowired
    private OrderItemRepo repo;

    @Override
    public OrderItemResponse addOrderItem(OrderItemRequest orderItemRequest) {
        OrderItem item = new OrderItem();
        item.setProductId(orderItemRequest.getProductId());
        item.setProductTitle(orderItemRequest.getProductTitle());
        item.setProductPrice(orderItemRequest.getProductPrice());
        item.setQuantity(orderItemRequest.getQuantity());
        item.setLineTotal(orderItemRequest.getLineTotal());
        item.setProductImage(orderItemRequest.getProductImage());

        repo.save(item);

        OrderItemResponse response = new OrderItemResponse();
        response.setProductId(item.getProductId());
        response.setProductTitle(item.getProductTitle());
        response.setProductPrice(item.getProductPrice());
        response.setQuantity(item.getQuantity());
        response.setLineTotal(item.getLineTotal());
        response.setProductImage(item.getProductImage());

        return response;
    }

    @Override
    public OrderItemResponse updateOrderItem(Long id, OrderItemRequest orderItemRequest) {
        OrderItem item = repo.findById(id).orElseThrow(() -> new RuntimeException("OrderItem not exist"));

        item.setProductId(orderItemRequest.getProductId());
        item.setProductTitle(orderItemRequest.getProductTitle());
        item.setProductPrice(orderItemRequest.getProductPrice());
        item.setQuantity(orderItemRequest.getQuantity());
        item.setLineTotal(orderItemRequest.getLineTotal());
        item.setProductImage(orderItemRequest.getProductImage());

        repo.save(item);

        OrderItemResponse response = new OrderItemResponse();
        response.setProductId(item.getProductId());
        response.setProductTitle(item.getProductTitle());
        response.setProductPrice(item.getProductPrice());
        response.setQuantity(item.getQuantity());
        response.setLineTotal(item.getLineTotal());
        response.setProductImage(item.getProductImage());

        return response;
    }

    @Override
    public String deleteOrderItem(Long id) {
        OrderItem item = repo.findById(id).orElseThrow(() -> new RuntimeException("OrderItem not exist"));
        repo.delete(item);
        return "OrderItem deleted successfully!";
    }

    @Override
    public List<OrderItem> getOrderItems() {
        return repo.findAll();
    }

    @Override
    public OrderItemResponse getOrderItemById(Long id) {
        OrderItem item = repo.findById(id).orElseThrow(() -> new RuntimeException("OrderItem not exist"));

        OrderItemResponse response = new OrderItemResponse();
        response.setProductId(item.getProductId());
        response.setProductTitle(item.getProductTitle());
        response.setProductPrice(item.getProductPrice());
        response.setQuantity(item.getQuantity());
        response.setLineTotal(item.getLineTotal());
        response.setProductImage(item.getProductImage());

        return response;
    }
}
