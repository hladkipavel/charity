package pl.coderslab.charity.category;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2)
    private String name;
    public Category(String name) {
        this.name = name;
    }
}
