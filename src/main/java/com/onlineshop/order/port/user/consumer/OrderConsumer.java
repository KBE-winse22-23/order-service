package com.onlineshop.order.port.user.consumer;


import com.onlineshop.order.core.domain.dto.OrderRequest;
import com.onlineshop.order.core.domain.service.impl.OrderService;
import com.onlineshop.order.port.config.MQConfig;
import com.onlineshop.order.port.user.exception.EmptyFieldException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = MQConfig.ORDER_QUEUE)
    public void createNewOrder(OrderRequest orderRequest) throws EmptyFieldException {
        System.out.println("------------------------------------------------------------------");

        System.out.println(orderRequest);

        orderService.saveOrder(orderRequest);
    }
}
