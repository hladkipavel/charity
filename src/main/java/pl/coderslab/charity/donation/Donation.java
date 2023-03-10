package pl.coderslab.charity.donation;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
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
@ToString
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=1)
    private Integer quantity;
    @ManyToMany
    @JoinTable(name = "donations_categories",
    joinColumns = @JoinColumn(name = "donation_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    private LocalTime pickUpTime;
    @Size(min=5)
    private String pickUpComment;

    public Donation(Integer quantity, String street, String city, String zipCode) {
        this.quantity = quantity;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
}
