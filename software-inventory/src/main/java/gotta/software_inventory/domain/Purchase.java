package gotta.software_inventory.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="purchase_id")
    private Long id;
    @Column(name="total_amount")
    private Double total;
    @Column(name="invoice_number")
    private String invoiceNumber;
    @Column(name="payment_method")
    private String paymentMethod;
    private String status;
    private String notes;

    @Column(name="date_purchase")
    private LocalDateTime purchaseRegister= LocalDateTime.now();


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

   @OneToMany(fetch = FetchType.LAZY,mappedBy = "purchase",cascade = CascadeType.ALL)
   @JsonManagedReference(value = "Purchase-PurchaseDetail")
    private List<PurchaseDetails> purchaseDetailsList =new ArrayList<>();


   public void invoiceNumberValid(){
       if(this.invoiceNumber==null || this.invoiceNumber.isBlank()){
           this.invoiceNumber=this.getSupplier().getContactName().substring(0,4)+"-"+System.currentTimeMillis();
       }
   }

   public  void paymentValid(){
       if(this.paymentMethod==null || this.paymentMethod.isBlank()){
           throw new RuntimeException("Debes ingresar el metodo de pago");
       }
   }



}
