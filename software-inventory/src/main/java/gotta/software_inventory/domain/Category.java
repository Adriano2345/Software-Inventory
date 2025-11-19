package gotta.software_inventory.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
@Table(name="category")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long id;

    @Column(name="category_name")
    private String categoryName;
    private String description;
    private Boolean status;
    @Column(name="created_at")
    private LocalDateTime categoryRegistration =LocalDateTime.now();

}

