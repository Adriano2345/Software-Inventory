package gotta.software_inventory.repository;

import gotta.software_inventory.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    Optional<Supplier> findByEmail(String email);
    Optional<Supplier>findById(Long id);
}
