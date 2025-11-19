package gotta.software_inventory.repository;

import gotta.software_inventory.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByDocumentId(String documentNumber);

    Optional<Customer> findByPhoneNumber(String phone);
}
