package com.cloderno.shoppingcart.request;

import com.cloderno.shoppingcart.model.Category;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private Long id;
    private String brand;
    private String name;
    private String description;
    private BigDecimal price;
    private int inventory;
    private Category category;
}
