package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.controllers.commands.RecipeFilter;
import edu.uph.ii.platformy.models.Category;
import edu.uph.ii.platformy.models.LevelOfDificulty;
import edu.uph.ii.platformy.models.Recipe;
import edu.uph.ii.platformy.repositories.IngredientRepository;
import edu.uph.ii.platformy.repositories.RecipeRepository;
import edu.uph.ii.platformy.repositories.StepOfRecipeRepository;
import edu.uph.ii.platformy.services.RecipeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Log4j2
public class RecipeListController
{
    @Autowired
    private RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    StepOfRecipeRepository stepOfRecipeRepository;

    @RequestMapping(path = "/recipes/", params = "id", method = RequestMethod.GET)
    public String showDetails(Model model, Long id)
    {
        Recipe recipe = recipeRepository.findAllById(id);
        if (recipe == null)
        {
            model.addAttribute("message","Nie odnaleziono przepisu");
            return "recipe/404";
        }
        model.addAttribute("stepsOfRecipe", stepOfRecipeRepository.findAllByRecipeIdOrderByIdAsc(id));
        model.addAttribute("ingredientList", ingredientRepository.findAllByRecipeId(id));
        model.addAttribute("recipe", recipe);

        return "recipe/recipeDetails";
    }

    @RequestMapping(path = "/recipes/", params = "did", method = RequestMethod.GET)
    public String deleteRecipe(Model model, Long did)
    {
        if(recipeRepository.existsById(did))
        {
            stepOfRecipeRepository.deleteAllByRecipeId(did);
            ingredientRepository.deleteAllByRecipeId(did);
            recipeRepository.deleteById(did);
        }


        return "redirect:/recipe";
    }

    @ModelAttribute("searchCommand")
    public RecipeFilter getSimpleSearch(){
        return new RecipeFilter();
    }

    @GetMapping(value="/recipes", params = {"all"})
    public String recipesList(@ModelAttribute("searchCommand") RecipeFilter search){
        search.clear();
        return "redirect:/recipes";
    }


    @RequestMapping(value="/recipes", method = {RequestMethod.GET, RequestMethod.POST})
    public String showRecipesList(Model model, @Valid @ModelAttribute("searchCommand") RecipeFilter search, BindingResult result, Pageable pageable){
        Page page;
        if(search.isEmpty()){
            page = recipeRepository.findAll(pageable);
        }else{
            page = recipeRepository.findAllByPhrase(search.getPhraseLIKE(),search.getCategory(), search.getLevelOfDificulty(), pageable);
        }

        model.addAttribute("recipeList", page);
        return "recipe/recipeList";
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

}
