package com.dagli.springboot.service.Impl;

import com.dagli.springboot.entity.Product;
import com.dagli.springboot.repository.ProductRepository;
import com.dagli.springboot.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> searchProducts(String query) {
        return productRepository.searchProducts(query);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
