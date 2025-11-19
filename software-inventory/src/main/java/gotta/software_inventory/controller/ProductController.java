package gotta.software_inventory.controller;

import gotta.software_inventory.domain.Product;
import gotta.software_inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?>postProduct(@RequestBody Product newProduct){
        Product pr= productService.postProduct(newProduct);
        URI located = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pr.getId()).toUri();
        return ResponseEntity.created(located).body(pr);
    }
}
