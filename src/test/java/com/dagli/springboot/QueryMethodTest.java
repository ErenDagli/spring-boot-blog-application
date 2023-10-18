package com.dagli.springboot;

import com.dagli.springboot.entity.Product;
import com.dagli.springboot.exception.ResourceNotFoundException;
import com.dagli.springboot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QueryMethodTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod() {

        Product product = productRepository.findByName("product 1");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByIdMethod() {
        Long id = 15L;
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", id));
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByNameOrDescriptionMethod() {
        List<Product> products = productRepository.findByNameOrDescription("product 1", "product 2 description");

        products.forEach(System.out::println);
    }

    @Test
    void findByNameAndDescriptionMethod() {
        List<Product> products = productRepository.findByNameAndDescription("product 1", "product 1 description");

        products.forEach(System.out::println);
    }

    @Test
    void findByDistinctByNameMethod() {
        Product product = productRepository.findDistinctByName("product 1");
        System.out.println(product);
    }

    @Test
    void findByIdGreaterThanMethod() {
        long id = 16L;
        List<Product> products = productRepository.findByIdGreaterThan(id);

        products.forEach(System.out::println);
    }

    @Test
    void findByIdLessThanMethod() {
        long id = 16L;
        List<Product> products = productRepository.findByIdLessThan(id);

        products.forEach(System.out::println);
    }

    @Test
    void findByNameContainingMethod() {
        List<Product> products = productRepository.findByNameContains("product");

        products.forEach(System.out::println);
    }

    @Test
    void findByNameLikeMethod() {
        List<Product> products = productRepository.findByNameLike("product 1");

        products.forEach(System.out::println);
    }

    @Test
    void findByIdBetweenMethod() {
        long startId = 15L;
        long endId = 20L;
        List<Product> products = productRepository.findByIdBetween(startId, endId);

        products.forEach(System.out::println);
    }

    @Test
    void findByDateCreatedBetweenMethod() {
        LocalDateTime startDate = LocalDateTime.of(2023, 9, 18, 10, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 11, 18, 10, 0, 0);

        List<Product> products = productRepository.findByDateCreatedBetween(startDate, endDate);

        products.forEach(System.out::println);

    }

    @Test
    void findByNameInMethod() {
        List<String> strings = List.of("product 1", "product 2");
        List<Product> products = productRepository.findByNameIn(strings);

        products.forEach(System.out::println);
    }

    @Test
    void findFirst2ByOrderByNameDescMethod() {
        List<Product> products = productRepository.findFirst2ByOrderByNameDesc();

        products.forEach(System.out::println);
    }
    @Test
    void findTop2ByOrderById() {
        List<Product> products = productRepository.findTop2ByOrderById();

        products.forEach(System.out::println);
    }
}
