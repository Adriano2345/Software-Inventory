package gotta.software_inventory.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="sales_details")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_detail_id")
    private Long id;
    private Integer quantity;
    @Column(name="unitPrice")
    private Double unitPrice;
    @Column(name="sub_total")
    private Double subTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sale_id")
    @JsonBackReference(value = "Sale-SaleDetails")
    private Sale sale;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;


}
