package pl.coderslab.charity.donation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/form")
public class DonationController {

    @GetMapping
    public String showFrom(Model model){
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        return "form";
    }
}
