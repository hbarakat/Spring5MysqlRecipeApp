package ma.aqary.recipeapp.repositories;

import ma.aqary.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Hamid Barakat
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
