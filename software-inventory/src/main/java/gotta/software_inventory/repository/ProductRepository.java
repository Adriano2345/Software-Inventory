package gotta.software_inventory.repository;

import gotta.software_inventory.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByProductNameAndUnitMeasure(String name,String measue);
}
