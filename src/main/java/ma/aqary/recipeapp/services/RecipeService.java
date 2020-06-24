package ma.aqary.recipeapp.services;

import ma.aqary.recipeapp.commands.RecipeCommand;
import ma.aqary.recipeapp.domain.Recipe;

import java.util.Set;

/**
 * @author Hamid Barakat
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long l);
   RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long id);

    void deleteById(Long idToDelete);
}
