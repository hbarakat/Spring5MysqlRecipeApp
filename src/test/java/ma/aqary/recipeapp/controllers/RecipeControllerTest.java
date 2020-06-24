package ma.aqary.recipeapp.controllers;

import ma.aqary.recipeapp.commands.RecipeCommand;
import ma.aqary.recipeapp.domain.Recipe;
import ma.aqary.recipeapp.exceptions.NotFoundException;
import ma.aqary.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Hamid Barakat
 */
class RecipeControllerTest {
@Mock
RecipeService recipeService;

RecipeController controller;
MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller=new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void getRecipeByIdTest() throws Exception {
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }
    @Test
    void getRecipeByIdNotFoundTest() throws Exception {
        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound())
        .andExpect(view().name("404error"));

    }

    @Test
    void getRecipeByIdNumberFormatExceptionTest() throws Exception {
        mockMvc.perform(get("/recipe/ss/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));

    }
    @Test
    void getRecipeFormTest() throws Exception {
        RecipeCommand recipeCommand=new RecipeCommand();
       // when(recipeService.findById(anyLong())).thenReturn(recipeCommand);
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }
    @Test
    void postNewRecipeFormTest() throws Exception {
        RecipeCommand recipeCommand =new RecipeCommand();
        recipeCommand.setId(2L);
        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);
        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","")
                .param("directions","some directions")
                .param("description","Some description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));

    }
    @Test
    void postNewRecipeFormatValidationFailTest() throws Exception {
        RecipeCommand recipeCommand =new RecipeCommand();
        recipeCommand.setId(2L);
        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);
        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","")
                .param("cookTime","5000")
                )

        .andExpect(status().isOk())
        .andExpect(model().attributeExists("recipe"))
        .andExpect(view().name("recipe/recipeform"));

    }


    @Test
    void deleteRecipeTest() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verify(recipeService,times(1)).deleteById(anyLong());
    }

}