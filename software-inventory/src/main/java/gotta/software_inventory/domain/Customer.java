package gotta.software_inventory.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="document_number_id")
    private String documentId;

    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="created_at")
    private LocalDateTime customerRegister = LocalDateTime.now();

    /*Metodo para validar ingreso de datos de documentID*/
    public void validNumberId(){
        if(this.documentId==null||this.documentId.isBlank()){
            throw  new RuntimeException("Debes ingresa el numero de identificacion del cliente para validar registro");
        }
    }


}
