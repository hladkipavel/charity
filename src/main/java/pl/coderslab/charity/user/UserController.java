package pl.coderslab.charity.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
}
