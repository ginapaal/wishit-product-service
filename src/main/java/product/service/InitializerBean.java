package product.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.model.Product;

import java.util.Currency;
import java.util.Locale;

@Component
public class InitializerBean {



    public InitializerBean(ProductService productService) {
        Product product = new Product("o", "k", "k", "k", 5.02f, Currency.getInstance("HUF"), 2);
        Product product2 = new Product("a", "a", "a", "a", 5.02f, Currency.getInstance("HUF"), 2);

        productService.uploadProduct(product);
        productService.uploadProduct(product2);
    }


}
