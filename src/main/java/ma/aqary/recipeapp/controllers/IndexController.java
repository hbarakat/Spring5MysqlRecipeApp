package ma.aqary.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import ma.aqary.recipeapp.domain.Category;
import ma.aqary.recipeapp.domain.UnitOfMeasure;
import ma.aqary.recipeapp.repositories.CategoryRepository;
import ma.aqary.recipeapp.repositories.UnitOfMeasureRepository;
import ma.aqary.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author Hamid Barakat
 */
@Slf4j
@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String getIndexPage(Model model){
        log.debug("Getting index Page");
       model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
