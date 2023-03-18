package pl.coderslab.charity.donation;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserRepository;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/form")
public class DonationController {
    private CategoryService categoryService;
    private InstitutionService institutionService;
    private DonationService donationService;
    private UserRepository userRepository;

    @GetMapping
    public String showFrom(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByEmail(username);
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        model.addAttribute("userName", user.getFirstName());
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return "admin/admin-form";
        }
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
