package gotta.software_inventory.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="purchases")
public class Purchase {
}
