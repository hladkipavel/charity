package pl.coderslab.charity.user;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(User user);
    User findByEmail(String email);
    List<User> findAllWithUserRole();
    User findById(Long id);
    void deleteById(Long id);
    List<User> findAllWithAdminRole();
    void saveAdmin(User admin);
}
