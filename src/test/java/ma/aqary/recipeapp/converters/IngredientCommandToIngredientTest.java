package ma.aqary.recipeapp.converters;

import ma.aqary.recipeapp.commands.CategoryCommand;
import ma.aqary.recipeapp.commands.IngredientCommand;
import ma.aqary.recipeapp.commands.UnitOfMeasureCommand;
import ma.aqary.recipeapp.domain.Ingredient;
import ma.aqary.recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hamid Barakat
 */
class IngredientCommandToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final Long UOM_ID = Long.valueOf(2L);

    IngredientCommandToIngredient conveter;

    @BeforeEach
    void setUp() {
        conveter=new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }
    @Test
    void isNullTest(){
        assertNull(null);
    }
    @Test
    void isEmptyTest(){
        assertNotNull(new Ingredient());
    }

    @Test
    void convert() {
        IngredientCommand ingredientCommand=new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        ingredientCommand.setUom(unitOfMeasureCommand);

        Ingredient ingredient= conveter.convert(ingredientCommand);
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

}