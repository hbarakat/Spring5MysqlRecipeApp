package ma.aqary.recipeapp.converters;

import ma.aqary.recipeapp.commands.CategoryCommand;
import ma.aqary.recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hamid Barakat
 */
class CategoryCommandToCategoryTest {
    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory conveter;

    @BeforeEach
    void setUp() {
        conveter = new CategoryCommandToCategory();
    }

    @Test
    void nullParameterTest(){
        assertNull(conveter.convert(null));
    }

    @Test
    void emptyParameterTest(){
        assertNotNull(conveter.convert(new CategoryCommand()));
    }


    @Test
    void convert() {
CategoryCommand categoryCommand =new CategoryCommand();
categoryCommand.setId(ID_VALUE);
categoryCommand.setDescription(DESCRIPTION);
        Category category = conveter.convert(categoryCommand);
        assertNotNull(category);
        assertEquals(ID_VALUE,category.getId());
        assertEquals(DESCRIPTION,category.getDescription());
    }
}