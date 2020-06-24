package ma.aqary.recipeapp.services;

import ma.aqary.recipeapp.commands.IngredientCommand;

/**
 * @author Hamid Barakat
 */

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteById(Long recipeId,Long idToDelete);
}
