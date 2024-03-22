package com.example.taskme;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.taskme.service.ProductService;
import com.example.taskme.api.ProductController;
import com.example.taskme.entity.Product;
import com.example.taskme.entity.User;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Mock
    private Model model;

    @Mock
    Principal principal = Mockito.mock(Principal.class);

    @Test
    public void productsTest() {
        // Arrange
        String title = "SomeTitle";
        List<Product> products = new ArrayList<>();
        Mockito.when(productService.listProducts(title)).thenReturn(products);

        // Act
        String result = productController.products("searchWord", title, principal, model);

        // Assert
        Mockito.verify(model).addAttribute("products", products);
        Mockito.verify(model).addAttribute("user", null); // Assuming no user retrieved
        Mockito.verify(model).addAttribute("searchWord", "searchWord");
        assertEquals("product", result);
    }

    @Test
    public void productInfoTest() {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        Mockito.when(productService.getProductById(id)).thenReturn(product);

        // Act
        String result = productController.productInfo(id, model, principal);

        // Assert
        Mockito.verify(model).addAttribute("user", null); // Assuming no user retrieved
        Mockito.verify(model).addAttribute("product", product);
        Mockito.verify(model).addAttribute("images", product.getImages());
        Mockito.verify(model).addAttribute("authorProduct", product.getUser());
        assertEquals("product-info", result);
    }
    
    @Test
    public void createProductTest() throws IOException {
        // Arrange
        MultipartFile file1 = Mockito.mock(MultipartFile.class);
        MultipartFile file2 = Mockito.mock(MultipartFile.class);
        MultipartFile file3 = Mockito.mock(MultipartFile.class);
        Product product = new Product();

        // Act
        String result = productController.createProduct(file1, file2, file3, product, principal);

        // Assert
        Mockito.verify(productService).saveProduct(principal, product, file1, file2, file3);
        assertEquals("redirect:/my/products", result);
    }


    @Test
    public void deleteProductTest() {
        // Arrange
        Long id = 1L;
        User user = new User();

        Mockito.when(productService.getUserByPrincipal(principal)).thenReturn(user);

        // Act
        String result = productController.deleteProduct(id, principal);

        // Assert
        Mockito.verify(productService).deleteProduct(user, id);
        assertEquals("redirect:/my/products", result);
    }


    @Test
    public void userProductsTest() {
        // Arrange
        User user = new User();
        List<Product> products = new ArrayList<>();
        user.setProducts(products);
        Mockito.when(productService.getUserByPrincipal(principal)).thenReturn(user);

        // Act
        String result = productController.userProducts(principal, model);

        // Assert
        Mockito.verify(model).addAttribute("user", user);
        Mockito.verify(model).addAttribute("products", products);
        assertEquals("my-products", result);
    }

}
