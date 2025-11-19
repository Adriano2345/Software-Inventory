package gotta.software_inventory.controller;

import gotta.software_inventory.domain.Category;
import gotta.software_inventory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> postCategory(@RequestBody Category newCategory) {
        Category c = categoryService.postCategory(newCategory);
        URI located = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId()).toUri();
        return ResponseEntity.created(located).body(c);
    }
    @PutMapping
    public ResponseEntity<?>putCategory(@RequestBody Category modCategory){
        Category category=categoryService.putCategory(modCategory);
        return ResponseEntity.ok(category);
    }
    @GetMapping
    public ResponseEntity<List<Category>>getCategory(){
        List<Category>c=categoryService.getCategorys();
        return ResponseEntity.ok(c);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}