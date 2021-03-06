package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.model.Product;
import product.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllByType(String type) {
        return productRepository.findAllByType(type);
    }

    public Product getProductById(int id) {
        return productRepository.findOne(id);
    }

    public List<Product> getAllInPriceRange(float minPrice, float maxPrice) {
        List<Product> allProducts = getAllProducts();
        List<Product> productsInPriceRange = new ArrayList<>();
        for (Product product: allProducts) {
            if (product.getDefaultPrice() >= minPrice && product.getDefaultPrice() <= maxPrice) {
                productsInPriceRange.add(product);
            }
        }
        return productsInPriceRange;
    }

    public void uploadProduct(String name,
                              String type,
                              String description,
                              String imageFileName,
                              float defaultPrice,
                              Currency defaultCurrency,
                              int ownerId) {
        Product product = new Product(name, type, description, imageFileName, defaultPrice, defaultCurrency, ownerId);
        productRepository.saveAndFlush(product);
    }

    public List<Product> getProductsByUser(int ownerId) {
        return productRepository.findAllByOwnerId(ownerId);
    }

    public void deleteProduct(int id) {
        productRepository.delete(getProductById(id));
    }

    public void update(int id, String name, String type,
                       String description, String imageFileName,
                       float defaultPrice, Currency defaultCurrency) {
        Product productToUpdate = getProductById(id);
        productToUpdate.setName(name);
        productToUpdate.setType(type);
        productToUpdate.setDescription(description);
        productToUpdate.setImageFileName(imageFileName);
        productToUpdate.setDefaultPrice(defaultPrice);
        productToUpdate.setDefaultCurrency(defaultCurrency);

        productRepository.saveAndFlush(productToUpdate);
    }

    public void uploadProduct(Product product) {
        productRepository.saveAndFlush(product);
    }



}
