package pl.coderslab.charity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);
    @Query("from User u join u.roles r where r.id = 1")
    List<User> findAllWithUserRole();
    @Query("from User u join u.roles r where r.id = 2")
    List<User> findAllWithAdminRole();
}
