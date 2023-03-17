package pl.coderslab.charity.institution;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class InstitutionController {
    private InstitutionService institutionService;

    @GetMapping("/institution")
    public String showInstitutionList(Model model){
        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions", institutions);
        return "/admin/institutions-list";
    }
}
