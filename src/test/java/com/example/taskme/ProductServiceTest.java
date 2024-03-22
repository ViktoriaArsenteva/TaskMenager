package com.example.taskme;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.example.taskme.entity.Product;
import com.example.taskme.repository.ProductRepository;
import com.example.taskme.service.ProductService;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void listProductsByTitleTest() {
        String title = "Product1";
        Product product1 = new Product();
        product1.setTitle("Product1");
        product1.setPrice(10);
        Product product2 = new Product();
        product2.setTitle("Product2");
        product2.setPrice(20);

        when(productRepository.findByTitle(title)).thenReturn(Arrays.asList(product1));

        List<Product> result = productService.listProducts(title);

        assertEquals(1, result.size());
        assertEquals("Product1", result.get(0).getTitle());
        assertEquals(10.0, result.get(0).getPrice());
    }

    @Test
    public void listAllProductsTest() {
        Product product1 = new Product();
        product1.setTitle("Product1");
        product1.setPrice(10);
        Product product2 = new Product();
        product2.setTitle("Product2");
        product2.setPrice(20);
        List<Product> productList = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.listProducts(null);

        assertEquals(2, result.size());
        assertEquals("Product1", result.get(0).getTitle());
        assertEquals(10.0, result.get(0).getPrice());
        assertEquals("Product2", result.get(1).getTitle());
        assertEquals(20.0, result.get(1).getPrice());
    }
}