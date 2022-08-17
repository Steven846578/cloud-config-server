package com.example.orderservice.service;

import com.example.orderservice.common.Payment;
import com.example.orderservice.common.TransactionRequest;
import com.example.orderservice.common.TransactionResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest transactionRequest)
    {
        String response = "";  //no response
        Order order=transactionRequest.getOrder();
        Payment payment=transactionRequest.getPayment();
        payment.setOrderId(order.getId());   // mapping the orderid to payment
        // Communication has to be done here using RestTemplate
        payment.setAmount(order.getPrice());    //communication has to be done here using

        Payment paymentResponse = restTemplate.postForObject("http://localhost:9092/payment/doPayment", payment, Payment.class);

        response=paymentResponse.getPaymentStatus().equals("Success")? "payment processing success and order placed" : "Payment failure in payment api - order added to cart";
        orderRepository.save(order);
        return new TransactionResponse (order, paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }
}
