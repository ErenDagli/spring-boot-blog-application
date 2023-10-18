package com.dagli.springboot.repository;

import com.dagli.springboot.entity.Product;
import com.dagli.springboot.exception.ResourceNotFoundException;
import com.dagli.springboot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod() {
        // create Product
        Product product = new Product();
        product.setName("product 1");
        product.setDescription("product 1 description");
        product.setSku("100ABC");
        product.setActive(true);
        product.setImageUrl("product1");

        Product savedProduct = productRepository.save(product);

        System.out.println(savedProduct.getId());
        System.out.println(savedProduct.toString());
    }

    @Test
    void updateUsingSaveMethod() {
        // find or retrieve an entity by id
        Long id = 7L;
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", id));

        // update entity information
        product.setName("updated product 1");
        product.setDescription("updated product 1 desc");

        // save updated entity
        Product savedProduct = productRepository.save(product);

        System.out.println(savedProduct);
    }

    @Test
    void findByIdMethod() {
        Long id = 7L;
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", id));
        System.out.println(product);
    }

    @Test
    void saveAllMethod() {
        Product product = new Product();
        product.setName("product 2");
        product.setDescription("product 2 description");
        product.setSku("100ABCD");
        product.setActive(true);
        product.setImageUrl("product2.png");

        Product product2 = new Product();
        product2.setName("product 3");
        product2.setDescription("product 3 description");
        product2.setSku("100ABCDE");
        product2.setActive(true);
        product2.setImageUrl("product3.png");

        List<Product> products = productRepository.saveAll(List.of(product, product2));

        products.forEach(System.out::println);
    }

    @Test
    void findAllMethod() {
        List<Product> products = productRepository.findAll();

        products.forEach(System.out::println);
    }

    @Test
    void deleteByIdMethod() {
        Long id = 2L;
        productRepository.deleteById(id);
        findAllMethod();
    }

    @Test
    void deleteMethod() {
        Long id = 13L;
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", id));
        System.out.println(product);
        productRepository.delete(product);
        findAllMethod();
    }

    @Test
    void deleteAllMethod() {
        //productRepository.deleteAll();
        findAllMethod();
        Product product = productRepository.findById(12L).orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", 12L));
        Product product2 = productRepository.findById(14L).orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", 14L));
        productRepository.deleteAll(List.of(product,product2));
    }

    @Test
    void countMethod() {
        long count = productRepository.count();
        System.out.println(count);
    }

    @Test
    void existsByIdMethod(){
        Long id = 13L;
        boolean result = productRepository.existsById(id);
        System.out.println(result);
    }
}
