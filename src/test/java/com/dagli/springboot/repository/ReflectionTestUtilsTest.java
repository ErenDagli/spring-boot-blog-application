package com.dagli.springboot.repository;

import com.dagli.springboot.dto.CategoryDto;
import com.dagli.springboot.entity.Category;
import com.dagli.springboot.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReflectionTestUtilsTest {

    @Autowired
    ApplicationContext context;

    Category category;

    CategoryDto categoryDto;

    @BeforeEach
    void categoryBeforeEach() {
        category = new Category();
        category.setName("ahmet");
        category.setDescription("ahmet description");
        categoryDto = new CategoryDto();
        categoryDto.setName("ahmet");
        categoryDto.setDescription("ahmet description");

        ReflectionTestUtils.setField(category,"id",100L);
        ReflectionTestUtils.setField(categoryDto,"id",101L);
    }

    @Test
    void getPrivateField() {
        assertEquals(100L,ReflectionTestUtils.getField(category,"id"));
        assertEquals(101L,ReflectionTestUtils.getField(categoryDto,"id"));
    }

    @Test
    void invokePrivateMethod() {
        assertEquals("ahmet ahmet description",
                ReflectionTestUtils.invokeMethod(categoryDto,"getNameAndDescription"),
                "Fail private method not call");
    }
}
