package pl.coderslab.charity.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }
    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String saveRegisterUser(User user){
        userService.saveUser(user);
        return "redirect:/login";
    }
    @GetMapping("/admin/users-list")
    public String showUsersList(Model model){
        List<User> users = userService.findAllWithUserRole();
        model.addAttribute("users", users);
        return "admin/users-list";
    }
    @GetMapping("/admin/user-edit/{id}")
    public String showUserEditForm(@PathVariable Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/admin/user-edit-form";
    }
    @PostMapping("/admin/user-edit")
    public String editUser(User user){
        userService.saveUser(user);
        return "redirect:/admin/users-list";
    }
    @GetMapping("/admin/user-details/{id}")
    public String showUserDetails(@PathVariable Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/admin/user-details";

    }
    @GetMapping("/admin/add-user")
    public String showAddUserForm(Model model){
        model.addAttribute("user", new User());
        return "/admin/user-add-form";
    }
    @PostMapping("/admin/add-user")
    public String addNewUser(User user){
        userService.saveUser(user);
        return "redirect:/admin/users-list";
    }
    @GetMapping("/admin/user-delete/{id}")
    public String showUserDeleteConfirmForm(@PathVariable Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/admin/user-delete-confirm";
    }
    @PostMapping("/admin/user-delete")
    public String deleteUserByAdmin(@RequestParam Long id){
        User user = userService.findById(id);
        user.setRoles(new HashSet<>());
        userService.deleteById(user.getId());
        return "redirect:/admin/users-list";
    }
    @GetMapping("/admin/user-block/{id}")
    public String showBlockUserConfirm(@PathVariable Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/admin/user-block-confirm";
    }
    @PostMapping("/admin/user-block")
    public String blockUserByAdmin(@RequestParam Long id){
        User user = userService.findById(id);
        user.setBlocked(true);
        userService.saveUser(user);
        return "redirect:/admin/users-list";
    }
    @GetMapping("/admin/user-unblock/{id}")
    public String unblockUserByAdmin(@PathVariable Long id){
        User user = userService.findById(id);
        user.setBlocked(false);
        userService.saveUser(user);
        return "redirect:/admin/users-list";
    }
    @PostMapping("/error")
    public String errorMessage(@RequestParam String username, Model model){
        User user = userService.findByEmail(username);
        if(user != null){
            if(user.isBlocked()){
                model.addAttribute("message", "Jesteś zablokowany/a przez administratora!");
            }else {
                model.addAttribute("message", "Podałeś/aś nipoprawne hasło!");
            }
        }else {
            model.addAttribute("message", "Podałeś/aś nipoprawny email!");
        }
        return "error-page";
    }
    @GetMapping("/admin/admins-list")
    public String showAdminsList(@AuthenticationPrincipal UserDetails username, Model model){
        List<User> admins = userService.findAllWithAdminRole();
        model.addAttribute("admins", admins);
        String authUserName = username.getUsername();
        model.addAttribute("authUsername", authUserName);
        System.out.println(username);
        return "/admin/admins-list";
    }
    @GetMapping("/admin/add-admin")
    public String showAddAdminForm(Model model){
        model.addAttribute("admin", new User());
        return "/admin/admin-add-form";
    }
    @PostMapping("/admin/add-admin")
    public String addNewAdmin(User admin){
        userService.saveAdmin(admin);
        return "redirect:/admin/admins-list";
    }
    @GetMapping("/admin/admin-details/{id}")
    public String showAdminDetails(@PathVariable Long id, Model model){
        User admin =  userService.findById(id);
        model.addAttribute("admin", admin);
        return "/admin/admin-details";
    }
    @GetMapping("/admin/admin-edit/{id}")
    public String showEditAdminForm(@PathVariable Long id, Model model){
        User admin = userService.findById(id);
        model.addAttribute("admin", admin);
        return "/admin/admin-edit-form";
    }
    @PostMapping("/admin/admin-edit")
    public String editAdminByAdmin(User admin){
        userService.saveAdmin(admin);
        return "redirect:/admin/admins-list";
    }
    @GetMapping("/admin/admin-delete/{id}")
    public String showDeleteFormConfirm(@PathVariable Long id, Model model){
        User admin = userService.findById(id);
        model.addAttribute("admin", admin);
        return "/admin/admin-delete-confirm";
    }
    @PostMapping("/admin/admin-delete")
        public String deleteAdminByAdmin(@RequestParam Long id){
        User admin = userService.findById(id);
        admin.setRoles(new HashSet<>());
        userService.deleteById(admin.getId());
        return "redirect:/admin/admins-list";
    }
    @GetMapping("/admin/admin-block/{id}")
    public String showBlockAdminForm(@PathVariable Long id, Model model) {
        User admin = userService.findById(id);
        model.addAttribute("admin", admin);
        return "/admin/admin-block-confirm";
    }
    @PostMapping("/admin/admin-block")
    public String blockAdminByAdmin(@RequestParam Long id){
        User admin = userService.findById(id);
        admin.setBlocked(true);
        userService.saveAdmin(admin);
        return "redirect:/admin/admins-list";
    }
    @GetMapping("/admin/admin-unblock/{id}")
    public String unblockAdminByAdmin(@PathVariable Long id){
        User admin = userService.findById(id);
        admin.setBlocked(false);
        userService.saveAdmin(admin);
        return "redirect:/admin/admins-list";
    }

}
