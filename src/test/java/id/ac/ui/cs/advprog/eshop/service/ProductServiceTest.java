package id.ac.ui.cs.advprog.eshop.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63db6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        when(productRepository.create(product)).thenReturn(product);

        Product created = productService.create(product);

        assertEquals(product, created);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product 1");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Test Product 2");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        List<Product> products = Arrays.asList(product, product2);
        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> allProducts = productService.findAll();

        assertFalse(allProducts.isEmpty());
        assertEquals(2, allProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        productRepository.create(product);
        when(productRepository.findById("1")).thenReturn(product);

        Product found = productService.findById("1");

        assertEquals(product, found);
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        when(productRepository.delete(product)).thenReturn(product);

        Product deleted = productService.delete(product);

        assertEquals(product, deleted);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testEditProduct() {
        Product originalProduct = new Product();
        originalProduct.setProductId("1");
        originalProduct.setProductName("Original Product");
        originalProduct.setProductQuantity(10);
        productRepository.create(originalProduct);

        Product editedProduct = new Product();
        editedProduct.setProductId("1");
        editedProduct.setProductName("Edited Product");
        editedProduct.setProductQuantity(20);

        when(productRepository.findById("1")).thenReturn(originalProduct);
        when(productRepository.edit(originalProduct, editedProduct)).thenReturn(editedProduct);

        Product result = productService.edit(editedProduct);

        assertEquals(editedProduct.getProductName(), result.getProductName());
        assertEquals(editedProduct.getProductQuantity(), result.getProductQuantity());
        verify(productRepository, times(1)).findById("1");
        verify(productRepository, times(1)).edit(originalProduct, editedProduct);
    }
}
