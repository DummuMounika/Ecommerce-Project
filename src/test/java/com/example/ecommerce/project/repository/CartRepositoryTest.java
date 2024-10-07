package com.example.ecommerce.project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.ecommerce.project.model.entity.AddToCartEntity;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    private AddToCartEntity sampleCartEntity;

    @BeforeEach
    //@Intializer
    void setUp() {
        sampleCartEntity = new AddToCartEntity();
        sampleCartEntity.setCustomerId(43); 
        sampleCartEntity.setProductId(43); 
        sampleCartEntity.setQuantity(1);
        sampleCartEntity.setUnitPrice(30);
        cartRepository.save(sampleCartEntity); 
    }
    
//    @Test
//    void testNew() {
//      sampleCartEntity = new AddToCartEntity();
//      sampleCartEntity.setCustomerId(43); 
//      sampleCartEntity.setProductId(43); 
//       AddToCartEntity addToCartEntity = cartRepository.save(sampleCartEntity); 
//       assertNotNull(addToCartEntity);
//    }

    @Test
    @DisplayName("Test 1: Find Current Timestamp")
    @Order(1)
    @Rollback(value = false) 
    void testFindCurrentTimeStamp() {
        Timestamp currentTimestamp = cartRepository.findCurrentTimeStamp();
        assertNotNull(currentTimestamp);
        System.out.println("Current Timestamp: " + currentTimestamp);
    }

    @Test
    @DisplayName("Test 2: Find by Customer ID and Product ID")
    @Order(2)
    void testFindByCustomerIdAndProductId() {
        Optional<AddToCartEntity> result = cartRepository.findByCustomerIdAndProductId(43, 43);
        assertThat(result).isPresent(); 

        result.ifPresent(entity -> {
            assertEquals(43, entity.getCustomerId());
            assertEquals(43, entity.getProductId());
        });
    }

    @Test
    @DisplayName("Test 3: Find All Items by Customer ID")
    @Order(3)
    void testFindAllByCustomerId() {
        List<AddToCartEntity> cartItems = cartRepository.findAllByCustomerId(1);
        assertThat(cartItems).isNotEmpty(); 
        assertThat(cartItems.size()).isGreaterThanOrEqualTo(1); 

        for (AddToCartEntity item : cartItems) {
            assertEquals(1, item.getCustomerId());
        }
    }

    @Test
    @DisplayName("Test 4: Find by Customer ID and Product ID - Not Found")
    @Order(4)
    public void testFindByCustomerIdAndProductIdNotFound() {
        Optional<AddToCartEntity> result = cartRepository.findByCustomerIdAndProductId(999, 999);
        assertThat(result).isEmpty();
    }
}
