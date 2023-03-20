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
        return "/admin/inst-list";
    }
    @GetMapping("/add-institution")
    public String showAddInstitutionForm(Model model){
        Institution institution = new Institution();
        model.addAttribute("institution", institution);
        return "/admin/inst-add-form";
    }
    @PostMapping("/add-institution")
    public String addNewInstitution(Institution institution){
        institutionService.saveInstitution(institution);
        return "redirect:/admin/institution";
    }
    @GetMapping("/inst-edit/{id}")
    public String editInstitutionForm(@PathVariable Long id, Model model){
        Institution institution = institutionService.findById(id);
        model.addAttribute("institution", institution);
        return "/admin/inst-edit-form";
    }
    @PostMapping("/inst-edit")
    public String saveEditedInstitution(Institution institution){
        institutionService.saveInstitution(institution);
        return "redirect:/admin/institution";
    }
    @GetMapping("/inst-delete/{id}")
    public String showDeleteConfirmForm(@PathVariable Long id, Model model){
        Institution institution = institutionService.findById(id);
        model.addAttribute("institution", institution);
        return "/admin/inst-delete-form";
    }
    @PostMapping("/inst-delete")
    public String deleteInstitution(@RequestParam Long id){
        institutionService.deleteInstitution(id);
        return "redirect:/admin/institution";
    }
    @GetMapping("/inst-details/{id}")
    public String showInstDetails(@PathVariable Long id, Model model){
        Institution institution = institutionService.findById(id);
        model.addAttribute("institution", institution);
        return "/admin/inst-details";
    }

}
