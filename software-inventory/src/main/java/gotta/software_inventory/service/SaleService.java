package gotta.software_inventory.service;

import gotta.software_inventory.domain.Customer;
import gotta.software_inventory.domain.Product;
import gotta.software_inventory.domain.Sale;
import gotta.software_inventory.domain.SaleDetail;
import gotta.software_inventory.repository.CustomerRepository;
import gotta.software_inventory.repository.ProductRepository;
import gotta.software_inventory.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Sale postSale(Sale newSale){
        double total=0;
        newSale.getCustomer().validNumberId();
        Customer customer;
        if(newSale.getCustomer()!=null){
            customer=customerService.findOrCreateCustomerOrDocumentNumber(newSale.getCustomer());
        }else{
            throw new RuntimeException("Debes ingresar datos del cliente para registros");
        }

        newSale.setCustomer(customer);


        for(SaleDetail s: newSale.getSaleDetailList()){
            s.getSale().paymentValid();
            Product product=s.getProduct();
            Product pr;

            if(s.getProduct()!=null){
                pr=productRepository.findById(product.getId()).orElseThrow(()->new RuntimeException("El producto no esta en el inventario"));
            }else{
                throw new RuntimeException("Debes ingresar el producto que vas a vender");
            }
            int newStock=pr.getStock()-s.getQuantity();
            pr.setStock(newStock);

            double subTotal=s.getUnitPrice()*s.getQuantity();
            s.setSubTotal(subTotal);
            total+=subTotal;


            s.setProduct(pr);
            s.setSale(newSale);

            productRepository.save(pr);


        }
        newSale.setTotal(total);
        newSale.invoiceNumberValid();
        return saleRepository.save(newSale);

    }
}
