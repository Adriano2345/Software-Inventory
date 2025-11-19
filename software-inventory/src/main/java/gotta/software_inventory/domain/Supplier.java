package gotta.software_inventory.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="supplier_id")
    private Long id;

    @Column(name="contact_name")
    private String contactName;
    private String phone;
    private String email;
    private String address;

    @Column(name="created_at")
    private LocalDateTime supplierRegister =LocalDateTime.now();

    //validar nombre del proveedor
    public void nameValid(){
        if(this.contactName==null || this.contactName.isBlank()){
            throw new RuntimeException("El proveedor debe de tener un nombre!");
        }
    }
    //valida el email del proveedor
    public void emailValid(){
        if(this.email==null || this.email.isBlank()){
            throw new RuntimeException("El proveedor debe de tener un email de contacto!");
        }
    }

    public void phoneNumberValid(){
        if(this.phone==null || this.phone.isBlank()){
            throw new RuntimeException("El proveedor debe de tener un numero de contacto!");
        }
    }
}
