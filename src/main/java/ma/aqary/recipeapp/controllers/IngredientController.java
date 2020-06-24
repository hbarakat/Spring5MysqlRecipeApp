package ma.aqary.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import ma.aqary.recipeapp.commands.IngredientCommand;
import ma.aqary.recipeapp.commands.RecipeCommand;
import ma.aqary.recipeapp.commands.UnitOfMeasureCommand;
import ma.aqary.recipeapp.services.IngredientService;
import ma.aqary.recipeapp.services.RecipeService;
import ma.aqary.recipeapp.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hamid Barakat
 */
@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;


    public IngredientController(RecipeService recipeService, IngredientService ingredientService,UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService =ingredientService;
        this.unitOfMeasureService =unitOfMeasureService;
    }
    @GetMapping("/recipe/{recipeId}/ingredients")
    public String ingredientList(@PathVariable String recipeId, Model model){
        log.debug("getting ingredient lis for recipe id:"+recipeId);
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }
    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String id, Model model){
        log.debug("getting ingredient By id"+id+" recipe id:"+recipeId);
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(id)));
        return "recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String addNewIngredient(@PathVariable String recipeId, Model model){
        RecipeCommand recipeCommand=recipeService.findCommandById((Long.valueOf(recipeId)));
        //Todo Raise Exception
        IngredientCommand ingredientCommand=new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("ingredient",ingredientCommand);
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,@PathVariable String id){
        log.debug("Delete id:"+id);
        ingredientService.deleteById(Long.valueOf(recipeId),Long.valueOf(id));
        return "redirect:/recipe/"+recipeId+"/ingredients";
    }
}

