package gotta.software_inventory.service;

import gotta.software_inventory.domain.Category;
import gotta.software_inventory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category postCategory(Category newCategory){
        Optional<Category> existCategory = categoryRepository.findByCategoryName(newCategory.getCategoryName());
        Category category;
        if(existCategory.isPresent()){
            category=existCategory.get();
        }else{
            category=new Category();
            category.setCategoryName(newCategory.getCategoryName());
            category.setDescription(newCategory.getDescription());
            category.setStatus(newCategory.getStatus());
            categoryRepository.save(category);
        }
        newCategory=category;

return categoryRepository.save(newCategory);
    }

    public void deleteCategory(Long id){
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
        }
    }

    public List<Category> getCategorys(){
        return categoryRepository.findAll();
    }

    public Category putCategory(Category modCategory){
        if(categoryRepository.existsById(modCategory.getId())){
            Category c= categoryRepository.findById(modCategory.getId()).get();
            c.setCategoryName(modCategory.getCategoryName());
            c.setDescription(modCategory.getDescription());
            c.setStatus(modCategory.getStatus());
            c.setCategoryRegistration(LocalDateTime.now());
            return  categoryRepository.save(c);
        }else{
            return null;
        }
    }

}
