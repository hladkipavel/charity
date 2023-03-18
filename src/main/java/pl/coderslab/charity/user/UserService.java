package pl.coderslab.charity.user;

import java.util.Optional;

public interface UserService {
    void saveUser(User user);
    User findByEmail(String email);
}
