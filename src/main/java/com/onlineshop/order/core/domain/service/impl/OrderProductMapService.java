package com.onlineshop.order.core.domain.service.impl;

import com.onlineshop.order.core.domain.model.OrderProductMap;
import com.onlineshop.order.core.domain.service.interfaces.OrderProductMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductMapService {
    @Autowired
    private OrderProductMapRepository orderProductMapRepository;

    public OrderProductMap saveOrderProductMap(OrderProductMap orderProductMap){
        return orderProductMapRepository.save(orderProductMap);
    }
}
