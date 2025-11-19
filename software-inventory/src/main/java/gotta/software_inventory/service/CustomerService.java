package gotta.software_inventory.service;

import gotta.software_inventory.domain.Customer;
import gotta.software_inventory.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


   /*Metodo para validar entrada de datos-si registra datos, retorna la busqueda -si encontro o si no crea */
    public Customer findOrCreateCustomerOrDocumentNumber(Customer newCustomer){
        if(newCustomer==null){
            throw new RuntimeException("No se ingresaron registros de cliente");
        }
        return customerRepository.findByDocumentId(newCustomer.getDocumentId()).orElseGet(()->customerRepository.save(newCustomer));
    }






}
