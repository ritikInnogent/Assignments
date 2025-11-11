package com.example.demo.Service;

import com.example.demo.Dto.OrderRequest;
import com.example.demo.Dto.OrderResponse;
import com.example.demo.Model.Address;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderItem;
import com.example.demo.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepo repo;


    @Override
    public OrderResponse addOrder(OrderRequest orderRequest) {
        Order orders = new Order();
        orders.setUserId(orderRequest.getUserId());
        orders.setFinalAmount(orderRequest.getFinalAmount());
        orders.setDiscountAmount(orderRequest.getDiscountAmount());
        orders.setTotalAmount(orderRequest.getTotalAmount());
        orders.setStatus(orderRequest.getStatus());
        orders.setPromoCodeUsed(orderRequest.getPromoCodeUsed());
        orders.setOrderDate(orderRequest.getOrderDate());
        orders.setDeliveryDate(orderRequest.getDeliveryDate());


        if (orderRequest.getOrderItems() != null && !orderRequest.getOrderItems().isEmpty()) {
            for (OrderItem item : orderRequest.getOrderItems()) {
                item.setOrder(orders);  // Set the Order reference on each OrderItem
            }
            orders.setOrderItems(orderRequest.getOrderItems());
        }

        if (orderRequest.getAddress() != null) {
            Address address = orderRequest.getAddress();
            address.setOrder(orders);
            orders.setAddress(orderRequest.getAddress());
        }

        repo.save(orders);

        OrderResponse  orderResponse = new OrderResponse();
        orderResponse.setDiscountAmount(orders.getDiscountAmount());
        orderResponse.setTotalAmount(orders.getTotalAmount());
        orderResponse.setStatus(orders.getStatus());
        orderResponse.setFinalAmount(orders.getFinalAmount());
        orderResponse.setPromoCodeUsed(orders.getPromoCodeUsed());
        orderResponse.setUserId(orders.getUserId());
        orderResponse.setOrderDate(orders.getOrderDate());
        orderResponse.setDeliveryDate(orders.getDeliveryDate());
        return orderResponse;
    }

    @Override
    public List<Order> getOrders() {
        return repo.findAll();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order orders = repo.findById(id).orElseThrow(()-> new RuntimeException("order not exist"));

        OrderResponse response = new OrderResponse();
        response.setUserId(orders.getUserId());
        response.setTotalAmount(orders.getTotalAmount());
        response.setDiscountAmount(orders.getDiscountAmount());
        response.setFinalAmount(orders.getFinalAmount());
        response.setStatus(orders.getStatus());
        response.setPromoCodeUsed(orders.getPromoCodeUsed());
        response.setOrderDate(orders.getOrderDate());
        response.setDeliveryDate(orders.getDeliveryDate());
        return response;
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest OrderRequest) {
        Order orders = repo.findById(id).orElseThrow(()-> new RuntimeException("order not exist"));

        orders.setUserId(OrderRequest.getUserId());
        orders.setFinalAmount(OrderRequest.getFinalAmount());
        orders.setDiscountAmount(OrderRequest.getDiscountAmount());
        orders.setTotalAmount(OrderRequest.getTotalAmount());
        orders.setStatus(OrderRequest.getStatus());
        orders.setPromoCodeUsed(OrderRequest.getPromoCodeUsed());
        orders.setOrderDate(OrderRequest.getOrderDate());
        orders.setDeliveryDate(OrderRequest.getDeliveryDate());
        repo.save(orders);

        OrderResponse  OrderResponse = new OrderResponse();
        OrderResponse.setDiscountAmount(orders.getDiscountAmount());
        OrderResponse.setTotalAmount(orders.getTotalAmount());
        OrderResponse.setStatus(orders.getStatus());
        OrderResponse.setFinalAmount(orders.getFinalAmount());
        OrderResponse.setPromoCodeUsed(orders.getPromoCodeUsed());
        OrderResponse.setUserId(orders.getUserId());
        OrderResponse.setOrderDate(orders.getOrderDate());
        OrderResponse.setDeliveryDate(orders.getDeliveryDate());
        return OrderResponse;
    }

    @Override
    public String deleteOrder(Long id) {
        Order orders = repo.findById(id).orElseThrow(() -> new RuntimeException("order not exist"));
        repo.delete(orders);
        return "Order deleted successfully!";
    }
}
