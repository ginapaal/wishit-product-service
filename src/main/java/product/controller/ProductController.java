package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
        return productService.getAllProducts();
    }

    @GetMapping("/products/type")
    public List<Product> getProductsByType(@ModelAttribute("typeName") String typeName) {
        return productService.getAllByType(typeName);
    }

    @GetMapping("/products/price")
    public List<Product> productsInPriceRange(@ModelAttribute("minPrice") float minPrice,
                                              @ModelAttribute("maxPrice") float maxPrice) {
        return productService.getAllInPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/products/{product_id}")
    public Product getSpecProduct(@PathVariable("product_id") int productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/{user_id}/products")
    public List<Product> getUsersProducts(@PathVariable("user_id") int userId) {
        return productService.getProductsByUser(userId);
    }

    @PostMapping("/{user_id}/products")
    public void uploadProduct(@PathVariable("user_id") int userId,
                              @RequestParam("name") String name,
                              @RequestParam("type") String type,
                              @RequestParam("description") String description,
                              @RequestParam("imageFileName") String imageFileName,
                              @RequestParam("defaultPrice") float defaultPrice,
                              @RequestParam("defaultCurrency") Currency currency) {
        productService.uploadProduct(name, type, description, imageFileName, defaultPrice, currency, userId);
    }

    @PutMapping("/{user_id}/products/{product_id}")
    public void updateProduct(@PathVariable("user_id") int userId,
                              @RequestParam("name") String name,
                              @RequestParam("type") String type,
                              @RequestParam("description") String description,
                              @RequestParam("imageFileName") String imageFileName,
                              @RequestParam("defaultPrice") float defaultPrice,
                              @RequestParam("defaultCurrency") Currency currency,
                              @PathVariable("product_id") int productId) {

        if (userId == productService.getProductById(productId).getOwnerId()) {
            productService.update(productId, name, type, description, imageFileName, defaultPrice, currency);
        } else {
            System.out.println("User ID and product owner's ID does not match");
        }
    }

    @DeleteMapping("/{user_id}/products/{product_id}")
    public void updateProduct(@PathVariable("user_id") int userId,
                              @PathVariable("product_id") int productId) {

        if (userId == productService.getProductById(productId).getOwnerId()) {
            productService.deleteProduct(productId);
        } else {
            System.out.println("User ID and product owner's ID does not match");
        }
    }

}
