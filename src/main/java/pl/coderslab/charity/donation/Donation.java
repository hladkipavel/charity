package pl.coderslab.charity.donation;

import lombok.*;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.institution.Institution;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=1)
    private Integer quantity;
    @OneToMany
    @JoinColumn(name = "donation_id")
    private List<Category> categories;
    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
    @Size(min=2)
    private String street;
    @Size(min=2)
    private String city;
    @Size(min=5)
    private String zipCode;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    @Size(min=5)
    private String pickUpComment;


}
