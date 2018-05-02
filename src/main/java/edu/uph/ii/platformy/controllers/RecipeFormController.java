package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.services.RecipeServiceImpl;
import edu.uph.ii.platformy.services.UserService;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;

import java.util.*;

@Controller
@SessionAttributes(names={"levelsOfDificulty", "categories"})
@Log4j2
public class RecipeFormController
{

    private RecipeServiceImpl recipeService;
    private UserService userService;

    public RecipeFormController(RecipeServiceImpl recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }


    @RequestMapping(value="/recipeForm", method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id)
    {

        List<Ingredient> ingredientList = new ArrayList<>();
        List<StepOfRecipe> listOfSteps = new ArrayList<>();
        Recipe recipe;
        if (! id.isPresent())
        {
            recipe = new Recipe();
            listOfSteps.add(new StepOfRecipe(""));
            ingredientList.add(new Ingredient("","", 0));
        }else
        {
            recipe = recipeService.getRecipe(id.get());
            ingredientList.addAll( recipeService.findAllIngredientsByRecipeId( recipe.getId()));
            listOfSteps.addAll( recipeService.findAllStepsofRecipeByRecipeId( recipe.getId()));
        }

        recipe.setIngredientList(ingredientList);
        recipe.setStepOfRecipeList(listOfSteps);

        model.addAttribute("recipe", recipe);

        return "recipe/recipeForm";
    }


    @PostMapping(value="/recipeForm")
    public String processForm(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult errors)
    {
        if(errors.hasErrors())
        {
            return "recipe/recipeForm";
        }

        for(Ingredient in : recipe.getIngredientList())
        {
            in.setRecipe(recipe);
        }
        for(StepOfRecipe st : recipe.getListOfSteps())
        {
            st.setRecipe(recipe);
        }

        recipeService.saveRecipe(recipe);
        recipeService.saveAllSteps(recipe.getListOfSteps());
        recipeService.saveAllIngrediens(recipe.getIngredientList());

        return "redirect:/recipes?all";
    }



    @ModelAttribute("levelsOfDificulty")
    public List<LevelOfDificulty> loadLevels()
    {
        return recipeService.findAllLevelsOfDificulty();
    }
    @ModelAttribute("categories")
    public List<Category> loadCategory()
    {
        return recipeService.findAllCategory();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.setDisallowedFields("0", "punkty");
    }
}
