package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query("SELECT SUM(d.quantity) FROM Donation d ")
    Integer countAllBagsGiven();
    @Query("SELECT COUNT(d) FROM Donation d")
    Integer sumAllDonations();
    @Query("SELECT d FROM Donation d where d.user = ?1")
    List<Donation> getAllDonationsByUserId(Long id);
}
