package gotta.software_inventory.controller;

import gotta.software_inventory.domain.Purchase;
import gotta.software_inventory.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<?> postPurchase(@RequestBody Purchase newPurchase){
        Purchase purchase = purchaseService.postPurchase(newPurchase);
        URI located = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(purchase.getId()).toUri();
        return ResponseEntity.created(located).body(purchase);
    }
}
