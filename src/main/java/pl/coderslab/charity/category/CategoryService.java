package pl.coderslab.charity.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public Category findById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }
    public void saveCategory(Category category){
        categoryRepository.save(category);
    }
    public void deleteCategory(Long id){
        boolean exist = categoryRepository.existsById(id);
            if(!exist){
                throw new RuntimeException("Not found category by this id");
            }
            categoryRepository.deleteById(id);
    }
}
