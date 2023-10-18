package com.dagli.springboot.repository;

import com.dagli.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("Select p From Product p where " +
            "p.name LIKE CONCAT('%',:query,'%') or " +
            "p.description LIKE CONCAT('%',:query,'%')")
    List<Product> searchProducts(String query);
    @Query(value = "Select * From products p where " +
            "p.name LIKE CONCAT('%',:query,'%') or " +
            "p.description LIKE CONCAT('%',:query,'%')",nativeQuery = true)
    List<Product> searchProductsSql(String query);


    // methodNameStrategy

    Product findByName(String name);

    Optional<Product> findById(Long id);

    List<Product> findByNameOrDescription(String name, String description);
    List<Product> findByNameAndDescription(String name, String description);

    Product findDistinctByName(String name);

    List<Product> findByIdGreaterThan(Long id);

    List<Product> findByIdLessThan(Long id);

    List<Product> findByNameContains(String name);

    List<Product> findByNameLike(String name);

    List<Product> findByIdBetween(Long startId, Long endId);

    List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Product> findByNameIn(List<String> names);

    List<Product> findFirst2ByOrderByNameDesc();

    List<Product> findTop2ByOrderById();

}
