package gotta.software_inventory.service;

import gotta.software_inventory.domain.Product;
import gotta.software_inventory.domain.Purchase;
import gotta.software_inventory.domain.PurchaseDetails;
import gotta.software_inventory.domain.Supplier;
import gotta.software_inventory.repository.ProductRepository;
import gotta.software_inventory.repository.PurchaseDetailRepository;
import gotta.software_inventory.repository.PurchaseRepository;
import gotta.software_inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private SupplierService supplierService;



    public Purchase postPurchase(Purchase newPurchase){
        double total=0;


        newPurchase.setPaymentMethod(newPurchase.getPaymentMethod());

        if(newPurchase.getSupplier()!=null){
            Supplier supplier;
            if(newPurchase.getSupplier().getId()!=null){
                supplier=supplierRepository.findById(newPurchase.getSupplier().getId()).orElseThrow(()->
                        new RuntimeException("El Id ingresado no coincide con proveedores registrados"));
            }else{
                supplier=supplierService.findOrCreateSupplier(newPurchase.getSupplier());
            }
            newPurchase.setSupplier(supplier);
        }

        newPurchase.invoiceNumberValid();




        for(PurchaseDetails p: newPurchase.getPurchaseDetailsList()){
            if(p.getProduct()==null ){
                throw new RuntimeException("!Debes ingresar los datos del producto que vas a comprar!");
            }
            p.getProduct().existMeasure();
            p.getProduct().existName();
            Optional<Product> existProduct= productRepository.findByProductNameAndUnitMeasure(p.getProduct().getProductName(),p.getProduct().getUnitMeasure());
            Product pr;
            if(existProduct.isPresent()){
                pr=existProduct.get();
            }else{
                pr=new Product();
                pr.setProductName(p.getProduct().getProductName());
                pr.setUnitMeasure(p.getProduct().getUnitMeasure());
                pr.setBrand(p.getProduct().getBrand());
                pr.setRegistrationProduct(LocalDateTime.now());
                pr.setPurchasePrice(p.getUnitPrice());
                if(pr.getStock()==null) pr.setStock(0);
                pr.skuAutomatic();
                pr.setCategory(p.getProduct().getCategory());
                productRepository.save(pr);
            }


            int newStock = p.getQuantity()+pr.getStock();
            pr.setStock(newStock);

            double subTotal= p.getUnitPrice()*p.getQuantity();
            p.setSubTotal(subTotal);
            total+=subTotal;


            p.setProduct(pr);
            p.setPurchase(newPurchase);


        }
        newPurchase.setTotal(total);

        return purchaseRepository.save(newPurchase);

    }
}
