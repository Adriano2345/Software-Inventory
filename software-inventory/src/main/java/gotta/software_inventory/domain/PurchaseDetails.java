package gotta.software_inventory.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="purchases_details")
public class PurchaseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pdetails_id")
    private Long id;

    @Column(name="subtotal")
    private Double subTotal;
    @Column(name="unit_price")
    private Double unitPrice;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference(value="Purchase-PurchaseDetail")
    @JoinColumn(name="purchase_id")
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;




}
