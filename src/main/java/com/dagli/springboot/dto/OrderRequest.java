package com.dagli.springboot.dto;

import com.dagli.springboot.entity.Order;
import com.dagli.springboot.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private Order order;
    private Payment payment;
}
