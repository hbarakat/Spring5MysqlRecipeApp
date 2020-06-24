package ma.aqary.recipeapp.controllers;

import ma.aqary.recipeapp.commands.IngredientCommand;
import ma.aqary.recipeapp.commands.RecipeCommand;
import ma.aqary.recipeapp.services.IngredientService;
import ma.aqary.recipeapp.services.RecipeService;
import ma.aqary.recipeapp.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Hamid Barakat
 */
public class IngredientControlerTest {
    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;
    IngredientController controller;
    @Mock
    UnitOfMeasureService unitOfMeasureService;
    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller=new IngredientController(recipeService,ingredientService,unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void listIngredientList() throws Exception {
    //Given
    RecipeCommand recipeCommand=new RecipeCommand();
    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
    //When
     mockMvc.perform(get("/recipe/1/ingredients"))
     .andExpect(status().isOk())
     .andExpect(view().name("recipe/ingredient/list"))
     .andExpect(model().attributeExists("recipe"));
    //Then
     verify(recipeService,times(1)).findCommandById(anyLong());
    }
    @Test
    public void showIngredientTest() throws Exception {
        //given
        IngredientCommand ingredientCommand=new IngredientCommand();
        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(ingredientCommand);
        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));

    }
    @Test
    public void  updateIngredientFormtest() throws Exception {
        //given
        IngredientCommand ingredientCommand=new IngredientCommand();
        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());
        //then
        mockMvc.perform(get("/recipe/1/ingredient/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }
    @Test
    void saveOrUpdateTest() throws Exception {
     //given
        IngredientCommand command =new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);
        //when
        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);
        //then
        mockMvc.perform(post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","")
                .param("description","some starting"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));

    }

    @Test
    public void addNewIngredientTest() throws Exception {
        //given
        RecipeCommand command=new RecipeCommand();
        command.setId(1L);
        //when
        when(recipeService.findCommandById(anyLong())).thenReturn(command);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());
        // then
        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("uomList"))
                .andExpect(model().attributeExists("ingredient"));
verify(recipeService,times(1)).findCommandById(anyLong());
    }
@Test
    public void deleteIngredientTest() throws Exception {
    mockMvc.perform(get("/recipe/1/ingredient/3/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/1/ingredients"));
    verify(ingredientService,times(1)).deleteById(anyLong(),anyLong());
}

}
