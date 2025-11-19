package gotta.software_inventory.repository;

import gotta.software_inventory.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByCategoryName(String name);
    Optional<Category>findById(Long id);
}
