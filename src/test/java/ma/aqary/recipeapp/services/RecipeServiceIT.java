package ma.aqary.recipeapp.services;

import ma.aqary.recipeapp.commands.RecipeCommand;
import ma.aqary.recipeapp.converters.RecipeCommandToRecipe;
import ma.aqary.recipeapp.converters.RecipeToRecipeCommand;
import ma.aqary.recipeapp.domain.Recipe;
import ma.aqary.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Hamid Barakat
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeServiceIT {
    public static final String NEW_DESRIPTION="description new";
    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    public void  setUp(){
    //    MockitoAnnotations.initMocks(this);
   //     recipeService=new RecipeServiceImpl(recipeRepository,recipeCommandToRecipe,recipeToRecipeCommand);
    }

    @Test
    @Transactional
    public void  testSaveDescription(){
        //given
        Iterable<Recipe> recipes= recipeRepository.findAll();
        Recipe testRecipe= recipes.iterator().next();
        RecipeCommand testRecipeCommand=recipeToRecipeCommand.convert(testRecipe);
        //when
        testRecipeCommand.setDescription(NEW_DESRIPTION);
        RecipeCommand savedRecipeCommand=recipeService.saveRecipeCommand(testRecipeCommand);
        //then
        assertEquals(NEW_DESRIPTION,savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(),savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(),savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(),savedRecipeCommand.getIngredients().size());

    }
}