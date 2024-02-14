package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        Product product = new Product();
        String expectedViewName = "createProduct";
        String actualViewName = productController.createProductPage(model);
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String expectedViewName = "redirect:list";
        String actualViewName = productController.createProductPost(product, model);
        assertEquals(expectedViewName, actualViewName);
        verify(productService, times(1)).create(product);
    }

    @Test
    void testProductListPage() {
        List<Product> allProducts = new ArrayList<>();
        when(productService.findAll()).thenReturn(allProducts);

        String expectedViewName = "productList";
        String actualViewName = productController.productListPage(model);
        assertEquals(expectedViewName, actualViewName);
        verify(model, times(1)).addAttribute("products", allProducts);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        String productId = product.getProductId();
        when(productService
                .findById(productId))
                .thenReturn(product);

        String expectedViewName = "redirect:/product/list";
        String actualViewName = productController.deleteProduct(productId);
        assertEquals(expectedViewName, actualViewName);
        verify(productService, times(1)).delete(product);
    }
    
    @Test
    void testEditProductPage() {
        Product product = new Product();
        String productId = product.getProductId();
        when(productService
                .findById(productId))
                .thenReturn(product);

        String expectedViewName = "editProduct";
        String actualViewName = productController.editProductPage(productId, model);
        assertEquals(expectedViewName, actualViewName);
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        String expectedViewName = "redirect:/product/list";
        String actualViewName = productController.editProductPost(product, product.getProductId(), model);
        assertEquals(expectedViewName, actualViewName);
        verify(productService, times(1)).edit(product);
    }
}