package pl.coderslab.charity.donation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/form")
public class DonationController {
    private CategoryService categoryService;

    @GetMapping
    public String showFrom(Model model){
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        return "form";
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
