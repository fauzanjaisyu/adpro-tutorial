package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(); // Assumed Product has a default constructor
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"));
        verify(productService, times(0)).create(any(Product.class));
    }

    @Test
    void testCreateProductPost() throws Exception {
        mockMvc.perform(post("/product/create")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
        verify(productService, times(1)).create(any(Product.class));
    }

//    @Test
//    void testProductListPage() throws Exception {
//        List<Product> allProducts = Arrays.asList(product);
//        given(productService.findAll()).willReturn(allProducts);
//
//        mockMvc.perform(get("/product/list"))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("products", allProducts));
//        verify(productService, times(1)).findAll();
//    }

    @Test
    void testDeleteProduct() throws Exception {
        when(productService.findById("1")).thenReturn(product);

        mockMvc.perform(get("/product/delete/{productId}", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).findById("1");
        verify(productService, times(1)).delete(product);
    }

//    @Test
//    void testEditProductPage() throws Exception {
//        given(productService.findById("1")).willReturn(product);
//
//        mockMvc.perform(get("/product/edit/{productId}", "1"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("product"));
//        verify(productService, times(1)).findById("1");
//    }

    @Test
    void testEditProductPost() throws Exception {
        mockMvc.perform(post("/product/edit/{productId}", "1")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
        verify(productService, times(1)).edit(any(Product.class));
    }
}