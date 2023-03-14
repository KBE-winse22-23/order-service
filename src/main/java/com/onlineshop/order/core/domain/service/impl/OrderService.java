package com.onlineshop.order.core.domain.service.impl;


import com.onlineshop.order.core.domain.dto.*;
import com.onlineshop.order.core.domain.model.*;
import com.onlineshop.order.core.domain.service.interfaces.OrderRepository;
import com.onlineshop.order.port.user.exception.EmptyFieldException;
import com.onlineshop.order.port.user.exception.NotFoundException;
import com.onlineshop.order.port.user.producer.OrderProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderProductMapService orderProductMapService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderProducer orderProducer;


    public List<MyOrder> getOrders() {
        return orderRepository.findAll();
    }


    public MyOrder saveOrder(OrderRequest orderRequest) throws EmptyFieldException {

        Owner owner = Owner.builder()
                .ownerFirstName(orderRequest.getOwnerFirstName())
                .ownerLastname(orderRequest.getOwnerLastName())
                .email(orderRequest.getEmail()).build();
        Address address = Address.builder()
                .city(orderRequest.getAddressCity())
                .country(orderRequest.getAddressCountry())
                .code(orderRequest.getAddressCode())
                .street(orderRequest.getAddressStreet())
                .number(orderRequest.getAddressNumber()).build();

        MyOrder order = MyOrder.builder()
                .date(new Date())
                .paymentId(orderRequest.getPaymentId())
                .cartId(orderRequest.getCartId())
                .build();

        MyOrder orderDB = orderRepository.save(order);

        orderDB.setAddress(address);
        orderDB.setOwner(owner);
        orderRepository.save(orderDB);

        double totalPrice = addProductsToOrderAndSumTotalPrice(orderDB, orderRequest.getProducts());

        orderDB.setTotalPrice(totalPrice);
        orderRepository.save(orderDB);
        decreaseProductsQuantityInProductService(orderRequest);
        return orderDB;
    }

    private void decreaseProductsQuantityInProductService(OrderRequest orderRequest){
        List<DecreaseProductQuantityDto> productsToDecreaseQuantityOf = new ArrayList<>();

        orderRequest.getProducts().forEach(p ->{
            productsToDecreaseQuantityOf.add(new DecreaseProductQuantityDto(p.getProductId(), p.getProductQuantity()));
        });

        productsToDecreaseQuantityOf.forEach(p ->{
            orderProducer.decreaseProductQuantity(p);
        });
    }

    private double addProductsToOrderAndSumTotalPrice(MyOrder order, List<ProductDto> products){
        final double[] totalPrice = new double[1];
            products.forEach(productDto -> {

            Product product = Product.builder().productId(productDto.getProductId())
                    .productName(productDto.getProductName())
                    .productPrice(productDto.getProductPrice())
                    .image(productDto.getImage())
                    .build();
            Product productSaved = productService.saveProduct(product);

            OrderProductMap orderProductMap = new OrderProductMap();
            orderProductMap.setMyOrder(order);
            orderProductMap.setProduct(productSaved);
            orderProductMap.setQuantity(productDto.getProductQuantity());
            totalPrice[0] = totalPrice[0] + (productDto.getProductPrice() * productDto.getProductQuantity());
            orderProductMapService.saveOrderProductMap(orderProductMap);
        });

            return totalPrice[0];
    }

    public MyOrder findOrderById(Long orderId) {
        return null;
    }


    public List<MyOrder> findOrderByOwner(Owner owner) {
        return null;
    }

    public List<OrderDto> getOrdersByEmail(String email) throws NotFoundException {
      List<MyOrder> myOrder = orderRepository.findByOwnerEmail(email);
        if(myOrder.isEmpty()){
            throw new NotFoundException("No orders found with the given email: " + email);
        }
      List<OrderDto> orderDtos = new ArrayList<>();

      myOrder.forEach(order ->{

          OrderDto orderDto = mapOrderToDto(order);

          orderDtos.add(orderDto);
      });
        return orderDtos;
    }

    public OrderDto getOrderByEmailAndPaymentId(String email, String paymentId) throws NotFoundException {
        Optional<MyOrder> myOrder = orderRepository.findByOwnerEmailAndPaymentId(email, paymentId);

        if(myOrder.isEmpty()){
            throw new NotFoundException("Order with with given email and paymentid not found");
        }

        return mapOrderToDto(myOrder.get());
    }

    private OrderDto mapOrderToDto(MyOrder myOrder){
        OrderDto orderDto = new OrderDto();
        orderDto.setPaymentId(myOrder.getPaymentId());
        orderDto.setOrderId(myOrder.getMyOrderId());
        orderDto.setDate(myOrder.getDate());
        orderDto.setTotalPrice(myOrder.getTotalPrice());
        orderDto.setProducts(mapOrderProductsToDto(myOrder));
        orderDto.setFirstname(myOrder.getOwner().getOwnerFirstName());
        orderDto.setLastname(myOrder.getOwner().getOwnerLastname());
        orderDto.setHouseNumber(myOrder.getAddress().getNumber());
        orderDto.setStreet(myOrder.getAddress().getStreet());
        orderDto.setCity(myOrder.getAddress().getCity());
        orderDto.setCode(myOrder.getAddress().getCode());
        orderDto.setCountry(myOrder.getAddress().getCountry());
        return orderDto;
    }

    private List<ProductDto> mapOrderProductsToDto(MyOrder myOrder){
        List<ProductDto> products = new ArrayList<>();
        myOrder.getOrderProductMaps().forEach(map ->{
            ProductDto productDto = ProductDto.builder()
                    .productId(map.getProduct().getProductId())
                    .productName(map.getProduct().getProductName())
                    .productPrice(map.getProduct().getProductPrice())
                    .image(map.getProduct().getImage())
                    .productQuantity(map.getQuantity())
                    .build();

            products.add(productDto);
        });

        return products;
    }

    public List<OrderCompactDto> getOrdersCompactByEmail(String email) throws NotFoundException {
        List<OrderCompactDto> ordersCompact = new ArrayList<>();

        getOrdersByEmail(email).forEach(order ->{
            OrderCompactDto orderCompact = new OrderCompactDto();
            orderCompact.setOrderId(order.getOrderId());
            orderCompact.setDate(order.getDate());
            orderCompact.setTotalPrice(order.getTotalPrice());
            orderCompact.setPaymentId(order.getPaymentId());

            ordersCompact.add(orderCompact);

        });

        return ordersCompact;
    }
}
