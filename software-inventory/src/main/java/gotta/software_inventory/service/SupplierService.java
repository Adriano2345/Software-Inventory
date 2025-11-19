package gotta.software_inventory.service;

import gotta.software_inventory.domain.Supplier;
import gotta.software_inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier postSupplier(Supplier newSupplier){
        return supplierRepository.save(newSupplier);
    }


    public Supplier findOrCreateSupplier(Supplier newSupllier){
        if(newSupllier==null){
            throw new RuntimeException("El proveedor es nulo");
        }
    return supplierRepository.findByEmail(newSupllier.getEmail()).orElseGet(()->supplierRepository.save(newSupllier));
    }




}
