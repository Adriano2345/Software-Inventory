package gotta.software_inventory.repository;

import gotta.software_inventory.domain.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleDetailRepository extends JpaRepository<SaleDetail,Long> {
}
