package gotta.software_inventory.service;

import gotta.software_inventory.domain.Category;
import gotta.software_inventory.domain.Product;
import gotta.software_inventory.repository.CategoryRepository;
import gotta.software_inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Product postProduct(Product newProduct){
        newProduct.existName();
        newProduct.existMeasure();

        Optional<Product> existProduct =productRepository.findByProductNameAndUnitMeasure(newProduct.getProductName(), newProduct.getUnitMeasure());
        Product pr;
        if(existProduct.isPresent()){
            pr=existProduct.get();
        }else{
            pr=new Product();
            if(pr.getStock()==null) pr.setStock(0);
            pr.setRegistrationProduct(LocalDateTime.now());
            pr.setProductName(newProduct.getProductName());
            pr.setUnitMeasure(newProduct.getUnitMeasure());
            pr.setDescription(newProduct.getDescription());
            pr.setBrand(newProduct.getBrand());
            pr.skuAutomatic();
            productRepository.save(pr);
        }
        if(newProduct.getCategory()!=null) {
            Optional<Category> existCategory = categoryRepository.findById(newProduct.getCategory().getId());
            Category category;
            if (existCategory.isPresent()) {
                category = existCategory.get();
                pr.setCategory(category);
            }
        }
            int newStock = pr.getStock() + newProduct.getStock();
            pr.setStock(newStock);
            newProduct = pr;

        return productRepository.save(newProduct);
    }
}
