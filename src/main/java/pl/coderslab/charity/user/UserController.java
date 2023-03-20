package pl.coderslab.charity.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
