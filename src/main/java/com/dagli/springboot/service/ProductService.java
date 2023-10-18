package com.dagli.springboot.service;

import com.dagli.springboot.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> searchProducts(String query);

    Product createProduct(Product product);
}
