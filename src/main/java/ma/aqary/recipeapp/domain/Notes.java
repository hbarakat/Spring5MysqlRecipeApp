package ma.aqary.recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Hamid Barakat
 */
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@OneToOne
private Recipe recipe;
@Lob
private String notes;
}
