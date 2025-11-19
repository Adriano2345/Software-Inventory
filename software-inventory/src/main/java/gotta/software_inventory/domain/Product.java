package gotta.software_inventory.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    @Column(name="product_name")
    private String productName;
    private String description;
    private String brand;
    @Column(name="unit_measure")
    private String unitMeasure;
    @Column(name="purchase_price")
    private Double purchasePrice;
    @Column(name="sale_price")
    private Double salePrice;
    @Column(name="current_stock")
    private Integer stock;
    @Column(name="minimum_stock")
    private Integer minimumStock;

    @Column(name="registration_product")
    private LocalDateTime registrationProduct= LocalDateTime.now();
    @Column(name="product_sku")
    private String productSku;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    //validacion productName
    public void existName(){
        if(this.productName==null || this.getProductName().isBlank()){
            throw new RuntimeException("El producto debe de tener un nombre!");
        }
    }
    //validacion unitMesaure
    public void existMeasure(){
        if(this.getUnitMeasure()==null || this.getUnitMeasure().isBlank()){
            throw new RuntimeException("El producto debe de tener un mesaure-o caracteristica unica!");
        }
    }

    //Creador de Sku Automatico
    public void skuAutomatic(){
        if(this.productSku==null || this.productSku.isBlank()){
            this.productSku=this.productName.substring(0,3).toUpperCase()+"-"+this.brand.substring(0,3).toUpperCase()+"-"+this.unitMeasure.substring(0,3).toUpperCase();
        }
    }

}
