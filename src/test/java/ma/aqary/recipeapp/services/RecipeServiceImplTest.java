package ma.aqary.recipeapp.services;

import ma.aqary.recipeapp.converters.RecipeCommandToRecipe;
import ma.aqary.recipeapp.converters.RecipeToRecipeCommand;
import ma.aqary.recipeapp.domain.Recipe;
import ma.aqary.recipeapp.exceptions.NotFoundException;
import ma.aqary.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Hamid Barakat
 */
class RecipeServiceImplTest {
 RecipeServiceImpl recipeService;
 @Mock
    RecipeRepository recipeRepository;


    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    public void  setUp(){
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository,recipeCommandToRecipe,recipeToRecipeCommand);
    }
    @Test
    void getRecipesTest() {
        Recipe recipe=new Recipe();
        HashSet<Recipe> recipeData=new HashSet<>();
        recipeData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipeData);
        Set<Recipe> recipes=recipeService.getRecipes();
        assertEquals(1,recipes.size());
        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    void getRecipeByIdTest() {
        Recipe recipe =new Recipe();
        recipe.setId(1L);
        Optional<Recipe> optionalRecipe=Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        Recipe returnedRecipe=recipeService.findById(1L);
        assertNotNull(returnedRecipe);
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }

    //@Test//(expected= NotFoundException.class)
    void getRecipeByIdTestNotFound(){
        Optional<Recipe> optionalRecipe=Optional.empty();
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
Recipe recipeReturned =recipeService.findById(1L);

    }
@Test
void RecipeNotFoundException(){
    //assertThrows(NotFoundException.class);
    Exception exception = assertThrows(NotFoundException.class, () ->
            getRecipeByIdTestNotFound());

}

    @Test
    void deleteRecipeById(){
        Long idToDelete=Long.valueOf(1L);
        recipeService.deleteById(idToDelete);
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }
}