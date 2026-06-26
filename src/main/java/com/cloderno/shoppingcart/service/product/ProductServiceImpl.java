package com.cloderno.shoppingcart.service.product;

import com.cloderno.shoppingcart.exception.CategoryNotFoundException;
import com.cloderno.shoppingcart.exception.ProductNotFoundException;
import com.cloderno.shoppingcart.model.Category;
import com.cloderno.shoppingcart.model.Product;
import com.cloderno.shoppingcart.repository.CategoryRepository;
import com.cloderno.shoppingcart.repository.ProductRepository;
import com.cloderno.shoppingcart.request.AddProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = categoryRepository.findById(
                request.getCategoryId()
        )
        .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getDescription(),
                request.getPrice(),
                request.getInventory(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return findProductById(id);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = findProductById(id);

        productRepository.delete(product);
    }

    @Override
    public void updateProduct(Long id, Product product) {

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory_Name(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategory_NameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }
}
