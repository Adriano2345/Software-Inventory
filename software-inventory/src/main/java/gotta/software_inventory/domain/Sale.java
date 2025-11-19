package gotta.software_inventory.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_id")
    private Long id;
    @Column(name="payment_method")
    private  String paymentMethod;
    @Column(name="invoice_number")
    private String invoiceNumber;
    @Column(name="total_amount")
    private Double total;

    @Column(name="sale_register")
    private LocalDateTime saleRegister =LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "sale",cascade = CascadeType.ALL)
    @JsonManagedReference(value="Sale-SaleDetails")
    private List<SaleDetail> saleDetailList =new ArrayList<>();

    public  void paymentValid(){
        if(this.paymentMethod==null || this.paymentMethod.isBlank()){
            throw new RuntimeException("Debes ingresar el metodo de pago");
        }
    }

    public void invoiceNumberValid(){
        if(this.invoiceNumber==null || this.invoiceNumber.isBlank()){
            this.invoiceNumber=this.customer.getDocumentId().substring(0,4)+"-"+System.currentTimeMillis();
        }
    }

}
