package gotta.software_inventory.repository;

import gotta.software_inventory.domain.PurchaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetails,Long> {
}
