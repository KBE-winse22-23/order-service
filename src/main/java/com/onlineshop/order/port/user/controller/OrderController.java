package com.onlineshop.order.port.user.controller;

import com.onlineshop.order.core.domain.dto.*;
import com.onlineshop.order.core.domain.model.MyOrder;
import com.onlineshop.order.core.domain.service.impl.OrderService;
import com.onlineshop.order.port.user.exception.EmptyFieldException;
import com.onlineshop.order.port.user.exception.NotFoundException;
import com.onlineshop.order.port.user.producer.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProducer orderProducer;


    @GetMapping
    public List<MyOrder> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping
    public MyOrder saveOrder(@RequestBody OrderRequest orderRequest) throws EmptyFieldException {
        return orderService.saveOrder(orderRequest);
    }

    @PostMapping("/get-by-email")
    public List<OrderDto> getOrdersByEmail(@RequestBody OwnerDto email) throws NotFoundException {
        System.out.println(email.getEmail());
        return orderService.getOrdersByEmail(email.getEmail());
    }

    @GetMapping("/get-by-email-and-payment/{email}/{paymentId}")
    public OrderDto getOrderByEmailAndPaymentId(@PathVariable("email") String email, @PathVariable("paymentId") String paymentId) throws NotFoundException {

        return orderService.getOrderByEmailAndPaymentId(email, paymentId);
    }

    @PostMapping("/get-by-email-compact")
    public List<OrderCompactDto> getOrdersCompactByEmail(@RequestBody OwnerDto email) throws NotFoundException {
        return orderService.getOrdersCompactByEmail(email.getEmail());
    }

    @PostMapping("/decrease-product-quantity")
    public String decreaseProductQuantity(@RequestBody DecreaseProductQuantityDto product){
        return orderProducer.decreaseProductQuantity(product);
    }



}
