package pl.coderslab.charity.donation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/form")
public class DonationController {
    private CategoryService categoryService;
    private InstitutionService institutionService;
    private DonationService donationService;

    @GetMapping
    public String showFrom(Model model){
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        return "form";
    }
    @PostMapping
    public String saveDonation(@ModelAttribute Donation donation){
        System.out.println(donation);
        donationService.saveDonation(donation);
        return "form-confirmation";
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions(){
        return institutionService.getAllInstitutions();
    }
}
