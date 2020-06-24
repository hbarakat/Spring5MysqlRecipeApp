package ma.aqary.recipeapp.converters;

import ma.aqary.recipeapp.commands.CategoryCommand;
import ma.aqary.recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hamid Barakat
 */
class CategoryToCategoryCommandTest {
    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final String DESCRIPTION = "description";
    CategoryToCategoryCommand conveter;

    @BeforeEach
    void setUp() {
        conveter=new CategoryToCategoryCommand();
    }
@Test
void isNullTest(){
        assertNull(null);
}
    @Test
    void isEmptyTest(){
        assertNotNull(new Category());
    }

    @Test
    void convert() {
        Category category=new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);
        CategoryCommand categoryCommand= conveter.convert(category);
        assertNotNull(categoryCommand);
        assertEquals(ID_VALUE,categoryCommand.getId());
        assertEquals(DESCRIPTION,categoryCommand.getDescription());

    }
}