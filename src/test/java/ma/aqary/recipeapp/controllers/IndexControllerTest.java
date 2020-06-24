package ma.aqary.recipeapp.controllers;

import ma.aqary.recipeapp.domain.Recipe;
import ma.aqary.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
/**
 * @author Hamid Barakat
 */
class IndexControllerTest {
 @Mock
    RecipeService recipeService;
    @Mock
    Model model;
    IndexController indexController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController=new IndexController(recipeService);
    }

    @Test
    void testMockMVC() throws Exception {
        MockMvc  mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    void getIndexPage() {
        //GIVEN
        Set<Recipe>  recipes=new HashSet<>();
        recipes.add(new Recipe());
        Recipe recipe =new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);
  when(recipeService.getRecipes()).thenReturn(recipes);
  ArgumentCaptor<Set<Recipe>> setArgumentCaptor= ArgumentCaptor.forClass(Set.class);
         //when
          String viewMame=indexController.getIndexPage(model);
        //then
          assertEquals("index",viewMame);
        verify(recipeService,times(1)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"),setArgumentCaptor.capture());
    Set<Recipe> setInController=setArgumentCaptor.getValue();
    assertEquals(2,setInController.size());
    }

}