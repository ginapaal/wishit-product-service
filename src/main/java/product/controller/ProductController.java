package product.controller;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.model.Product;
import product.service.ProductService;

import java.util.Currency;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        return allProducts;
    }

    @GetMapping("/products/type")
    public List<Product> getProductsByType(@ModelAttribute("typeName") String typeName) {
        List<Product> productsByType = productService.getAllByType(typeName);
        return productsByType;
    }

    @GetMapping("/products/price")
    public List<Product> productsInPriceRange(@ModelAttribute("minPrice") float minPrice,
                                              @ModelAttribute("maxPrice") float maxPrice) {
        List<Product> productsByPrice = productService.getAllInPriceRange(minPrice, maxPrice);
        return productsByPrice;
    }

    @GetMapping("/products/:product_id")
    public Product getSpecProduct(@PathVariable("product_id") int productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/:user_id/products")
    public List<Product> getUsersProducts(@PathVariable("user_id") int userId) {
        List<Product> usersProducts = productService.getProductsByUser(userId);
        return usersProducts;
    }

    @PutMapping("/:user_id/products")
    public void saveProduct(@PathVariable("user_id") int userId,
                              @RequestParam("name") String name,
                              @RequestParam("type") String type,
                              @RequestParam("description") String description,
                              @RequestParam("imageFileName") String imageFileName,
                              @RequestParam("defaultPrice") float defaultPrice,
                              @RequestParam("defaultCurrency") Currency currency) {
        productService.uploadProduct(name, type, description, imageFileName, defaultPrice, currency, userId);
    }

    @PostMapping("/:user_id/products/:product_id")
    public void updateProduct(@PathVariable("user_id") int userId,
                              @RequestParam("name") String name,
                              @RequestParam("type") String type,
                              @RequestParam("description") String description,
                              @RequestParam("imageFileName") String imageFileName,
                              @RequestParam("defaultPrice") float defaultPrice,
                              @RequestParam("defaultCurrency") Currency currency,
                              @PathVariable("product_id") int productId) {

        productService.update(productId, name, type, description, imageFileName, defaultPrice, currency);
    }

}
