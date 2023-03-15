package pl.coderslab.charity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import java.util.List;


@Controller
@AllArgsConstructor
public class HomeController {
    private InstitutionService institutionService;
    private DonationService donationService;


    @GetMapping("/")
    public String homeAction(Model model){
        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions", institutions);
        model.addAttribute("countBags", donationService.getCountAllBagsGiven());
        model.addAttribute("sumAllDonations", donationService.getSumAllDonations());
        return "index";
    }
}
