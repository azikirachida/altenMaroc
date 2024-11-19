package com.example.alten.service;
import com.example.alten.AltenApplication;
import com.example.alten.entity.Product;
import com.example.alten.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AltenApplication.class)
@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProduct() {
        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product(1,"123456","TV LG","tv 60","tv_tg.jpg","tv", BigDecimal.valueOf(100),100,"ABC123456","INSTOCK",123456,6,new Date(),null));
        productList.add(new Product(2,"225588","TV SONY","tv 55","tv_sony.jpg","tv",BigDecimal.valueOf(200),200,"FDRY123","OUTOFSTOCK",485697,6,new Date(),null));
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.getProducts();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1,"123456","TV LG","tv 60","tv_tg.jpg","tv",BigDecimal.valueOf(200),100,"ABC123456","INSTOCK",123456,6,new Date(),null);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        Optional<Product> resultOpt = Optional.ofNullable(productService.getProductById(1));
        Product result = resultOpt.get();
        assertEquals(1, result.getId());
        assertEquals("TV LG", result.getName());
    }

    @Test
    public void saveProduct() {
        Product product = new Product(3,"123456","TV LG","tv 60","tv_tg.jpg","tv",BigDecimal.valueOf(200),100,"ABC123456","INSTOCK",123456,6,new Date(),null);
        when(productRepository.save(product)).thenReturn(product);
        Product result = productService.saveProduct(product);
        assertEquals(3, result.getId());
        assertEquals("TV LG", result.getName());
    }

    @Test
    public void removeProduct() {
        Product product = new Product(1,"123456","TV LG","tv 60","tv_tg.jpg","tv",BigDecimal.valueOf(200),100,"ABC123456","INSTOCK",123456,6,new Date(),null);
        productService.deleteProduct(1);
        verify(productRepository, times(1)).delete(product);
    }
}
