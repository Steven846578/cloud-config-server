package com.example.orderservice.common;
import com.example.orderservice.entity.Order;
import lombok.Data;

@Data
public class TransactionRequest {
    private Order order;
    private Payment payment;
}