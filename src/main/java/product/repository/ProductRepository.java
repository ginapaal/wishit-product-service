package product.repository;

import org.springframework.stereotype.Repository;
import product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByType(String type);

    List<Product> findAllByOwnerId(int ownerId);
}
