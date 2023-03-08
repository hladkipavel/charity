package pl.coderslab.charity.institution;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InstitutionService {
    private InstitutionRepository institutionRepository;
    public List<Institution> getAllInstitutions(){
        return institutionRepository.findAll();
    }
    public Institution findById(Long id){
        return institutionRepository.getById(id);
    }
    public void saveInstitution(Institution institution){
        institutionRepository.save(institution);
    }
    public void deleteInstitution(Long id){
        boolean exist = institutionRepository.existsById(id);
        if(!exist){
            throw new RuntimeException("Not found institution by id");
        }
        institutionRepository.deleteById(id);
    }
}
