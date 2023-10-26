package com.dagli.springboot;

import com.dagli.springboot.dto.CategoryDto;
import com.dagli.springboot.entity.Category;
import com.dagli.springboot.exception.ResourceNotFoundException;
import com.dagli.springboot.repository.CategoryRepository;
import com.dagli.springboot.repository.ProductRepository;
import com.dagli.springboot.service.CategoryService;
import com.dagli.springboot.service.Impl.CategoryServiceImpl;
import com.dagli.springboot.service.ProductService;
import com.dagli.springboot.unittest.CollegeStudent;
import com.dagli.springboot.unittest.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MockAnnotationTest {

    @Mock
    private ModelMapper modelMapper;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    CategoryDto categoryDto;

    Category category,category1,category2;

    @BeforeEach
    public void beforeEach() {
        categoryDto = new CategoryDto();
        category = new Category();
        category1 = new Category();
        category2 = new Category();
        categoryDto.setId(1L);
        categoryDto.setName("books");
        categoryDto.setDescription("books description");
        category.setId(1L);
        category.setName("books");
        category.setDescription("books description");
        category1.setId(2L);
        category1.setName("books2");
        category1.setDescription("books2 description");
        category2.setId(3L);
        category2.setName("books3");
        category2.setDescription("books3 description");
    }

    @DisplayName("When & Verify")
    @Test
    public void assertEqualsCategoryDto() {
        System.out.println(category.getId());
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);

        CategoryDto result = categoryService.getCategory(1L);

        System.out.println(result);
        System.out.println(result.getName());
        System.out.println(result.getDescription());

        assertNotNull(result);
    }

    @DisplayName("get All categories test")
    @Test
    void assertEqualsCategoryList() {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(category);
        categories.add(category1);
        categories.add(category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDto> result = categoryService.getAllCategories();

        verify(categoryRepository,times(1)).findAll();

        result.forEach(System.out::println);

        assertNotNull(result);
        assertEquals(3,result.size());
    }

    @DisplayName("Resource Not Found Exception")
    @Test
    void assertEqualsResourceNotFoundException() {
        when(categoryRepository.findById(0L))
                                .thenThrow(new ResourceNotFoundException("Category", "id", 0L))
                                .thenReturn(null);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);

        assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.getCategory(0L);
        });

        assertDoesNotThrow(() -> categoryService.getCategory(1L));

        verify(categoryRepository,times(1)).findById(0L);
    }
}
