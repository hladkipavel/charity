package pl.coderslab.charity.donation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DonationService {
    private DonationRepository donationRepository;
    public List<Donation> getAllDonations(){
        return donationRepository.findAll();
    }
    public Donation findById(Long id){
        return donationRepository.findById(id).orElse(null);
    }
    public void saveDonation(Donation donation){
        donationRepository.save(donation);
    }
    public Integer getCountAllBagsGiven(){
        return donationRepository.countAllBagsGiven();
    }
    public Integer getSumAllDonations(){
        return donationRepository.sumAllDonations();
    }
    public List<Donation> getDonationsByUserId(Long id){
        return donationRepository.getAllDonationsByUserId(id);
    }
}
