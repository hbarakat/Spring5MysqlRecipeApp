package ma.aqary.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import ma.aqary.recipeapp.commands.RecipeCommand;
import ma.aqary.recipeapp.converters.RecipeCommandToRecipe;
import ma.aqary.recipeapp.converters.RecipeToRecipeCommand;
import ma.aqary.recipeapp.domain.Recipe;
import ma.aqary.recipeapp.exceptions.NotFoundException;
import ma.aqary.recipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Hamid Barakat
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{
private final RecipeRepository recipeRepository;
private final RecipeCommandToRecipe recipeCommandToRecipe;
private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
      log.debug("im in service");
        Set<Recipe> recipeSet=new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
    return recipeSet;
    }
    @Override
    public Recipe findById(Long id) {

        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (!recipe.isPresent()) {
            throw new NotFoundException("Recipe Not Found! for Id Value: "+id.toString());
       }
        return recipe.get();
    }
    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe= recipeCommandToRecipe.convert(command);
        Recipe savedRecipe=recipeRepository.save(detachedRecipe);
        log.debug("Saved Recipe:"+savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    public void deleteById(Long idToDelete) {
     recipeRepository.deleteById(idToDelete);
    }


}