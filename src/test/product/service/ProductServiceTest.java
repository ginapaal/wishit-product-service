package product.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import product.model.Product;
import product.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    public void testIfGetAllWorks() {
        Product product = new Product();
        Product product2 = new Product();

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product2);

        when(productService.getAllProducts()).thenReturn(productList);

        assertEquals(2, productService.getAllProducts().size());
    }

    @Test
    public void testIfGetByTypeWorks() {
        Product product = new Product("o", "book", "k", "k", 5.02f, Currency.getInstance("HUF"), 1);
        Product product1 = new Product("o", "book", "k", "k", 5.02f, Currency.getInstance("HUF"), 1);
        Product product2 = new Product("o", "dish", "k", "k", 5.02f, Currency.getInstance("HUF"), 1);
        List<Product> books = new ArrayList<>();
        books.add(product);
        books.add(product1);

        String filterType = "book";

        when(productService.getAllByType(filterType)).thenReturn(books);

        List<Product> myBooks = productService.getAllByType(filterType);

        boolean isBook = myBooks.contains(product2);

        assertAll("Check if size and boolean is ok",
                () -> assertEquals(2, myBooks.size()),
                () -> assertFalse(isBook));
    }

    @Test
    public void testIsGetAllInPriceRangeWorksAsExpected() {
        Product product = new Product("o", "book", "k", "k", 5.02f, Currency.getInstance("HUF"), 1);
        Product product1 = new Product("o", "book", "k", "k", 4f, Currency.getInstance("HUF"), 1);

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productService.getAllInPriceRange(5f, 8f)).thenReturn(productList);

        assertEquals(1, productService.getAllInPriceRange(5f, 8f).size());
    }

    @Test
    public void testIsGetUsersProductsWorksFine() {
        Product product = new Product("o", "book", "k", "k", 5.02f, Currency.getInstance("HUF"), 1);
        Product product1 = new Product("o", "book", "k", "k", 4f, Currency.getInstance("HUF"), 1);

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);

        when(productService.getProductsByUser(1)).thenReturn(productList);

        assertEquals(2, productService.getProductsByUser(1).size());
    }



}