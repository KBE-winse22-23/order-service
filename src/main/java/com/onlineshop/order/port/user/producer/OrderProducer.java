package com.onlineshop.order.port.user.producer;

import com.onlineshop.order.core.domain.dto.DecreaseProductQuantityDto;
import com.onlineshop.order.port.config.MQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    @Autowired
    private RabbitTemplate template;


    public String decreaseProductQuantity(DecreaseProductQuantityDto product) {
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.PRODUCT_UPDATE_ROUTING_KEY,product);

        return "Decrease Product Quantity Message sent to Product-Service";
    }
}
