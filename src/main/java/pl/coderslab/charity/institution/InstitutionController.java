package pl.coderslab.charity.institution;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/edit/{id}")
    public String editInstitutionForm(@PathVariable Long id, Model model){
        Institution institution = institutionService.findById(id);
        model.addAttribute("institution", institution);
        return "/admin/inst-edit-form";
    }
    @PostMapping("/edit")
    public String saveEditedInstitution(Institution institution){
        Institution institutionBD = institutionService.findById(institution.getId());
        institutionBD.setName(institution.getName());
        institutionBD.setDescription(institution.getDescription());
        institutionService.saveInstitution(institutionBD);
        return "redirect:/institution";
    }

}
