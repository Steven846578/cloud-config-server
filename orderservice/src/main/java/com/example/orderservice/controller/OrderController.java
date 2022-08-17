package com.example.orderservice.controller;

import com.example.orderservice.common.TransactionRequest;
import com.example.orderservice.common.TransactionResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")

public class OrderController {
    //@Autowired
    private OrderService orderService;


    @PostMapping("/bookorder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest)
    {
        return orderService.saveOrder(transactionRequest);
        //return orderService.saveOrder(order);
    }
}

