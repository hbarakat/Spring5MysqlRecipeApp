package ma.aqary.recipeapp.converters;

import ma.aqary.recipeapp.commands.IngredientCommand;
import ma.aqary.recipeapp.commands.UnitOfMeasureCommand;
import ma.aqary.recipeapp.domain.Ingredient;
import ma.aqary.recipeapp.domain.Recipe;
import ma.aqary.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hamid Barakat
 */
class IngredientToIngredientCommandTest {


    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final Long UOM_ID = Long.valueOf(2L);

    IngredientToIngredientCommand conveter;

    @BeforeEach
    void setUp() {
        conveter=new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }
    @Test
    void isNullTest(){
        assertNull(null);
    }
    @Test
    void isEmptyTest(){
        assertNotNull(new IngredientCommand());
    }

    @Test
    void convert() {
        Ingredient ingredient=new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);
        ingredient.setUom(unitOfMeasure);

        IngredientCommand ingredientCommand= conveter.convert(ingredient);
        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUom());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(UOM_ID, ingredientCommand.getUom().getId());
    }

}