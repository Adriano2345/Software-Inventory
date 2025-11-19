package gotta.software_inventory.repository;

import gotta.software_inventory.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale,Long> {

}
