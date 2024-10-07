package com.example.ecommerce.project.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ecommerce.project.model.entity.CategoryEntity;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    //@Test
    @DisplayName("Test 1: Find Current Timestamp")
    @Order(1)
    void testFindCurrentTimeStamp() {
        Timestamp timestamp = categoryRepository.findCurrentTimeStamp();
        assertThat(timestamp).isNotNull();
    }

    @Test
    @DisplayName("Test 2: Find by Category Name")
    @Order(2)
    void testFindByName() {
        CategoryEntity category = new CategoryEntity();
        category.setCategoryId(1);
        category.setCategoryName("Electronics");

        categoryRepository.save(category);

        CategoryEntity foundCategory = categoryRepository.findByName("Electronics");

        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getCategoryName()).isEqualTo("Electronics");
    }

    @Test
    @DisplayName("Test 3: Find by Customer Name - Not Found")
    @Order(3)
    void testFindByName_NotFound() {
        // Attempt to find a non-existent category
        CategoryEntity foundCategory = categoryRepository.findByName("NonExistentCategory");
        
        assertThat(foundCategory).isNull();
    }
}

