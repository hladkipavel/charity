package pl.coderslab.charity.institution;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "institutions")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2)
    private String name;
    @Min(10)
    @Max(200)
    private String description;
    public Institution(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
