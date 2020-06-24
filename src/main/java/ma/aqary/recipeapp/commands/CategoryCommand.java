package ma.aqary.recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hamid Barakat
 */
@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;
}
