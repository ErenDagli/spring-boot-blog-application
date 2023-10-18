package com.dagli.springboot.service;

import com.dagli.springboot.dto.OrderRequest;
import com.dagli.springboot.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderRequest);
}
