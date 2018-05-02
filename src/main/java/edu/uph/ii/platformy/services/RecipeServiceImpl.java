package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.config.ProfileNames;
import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile(ProfileNames.DATABASE)
public class RecipeServiceImpl implements  RecipeService
{
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private LevelOfDificultyRepository levelOfDificultyRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private StepOfRecipeRepository stepOfRecipeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveRecipe(Recipe recipe)
    {
        recipeRepository.save(recipe);
    }

    @Override
    public void saveAllSteps(List<StepOfRecipe> stepOfRecipeList) {
        stepOfRecipeRepository.saveAll(stepOfRecipeList);
    }

    @Override
    public void saveAllIngrediens(List<Ingredient> ingredientList) {
        ingredientRepository.saveAll(ingredientList);
    }

    @Override
    public void deleteAllIngredientByRecipeId(long id) {
        List<Ingredient> ingredientList = ingredientRepository.findAllByRecipeId(id);
        ingredientRepository.deleteAll(ingredientList);
    }

    @Override
    public void deleteAllStepsOfRecipeByRecipeId(long id) {
        List<StepOfRecipe> stepOfRecipes = stepOfRecipeRepository.findAllByRecipeId(id);
        stepOfRecipeRepository.deleteAll(stepOfRecipes);
    }

    @Override
    public void deleteRecipe(long id) {
        Recipe recipe = recipeRepository.findAllById(id);
        recipeRepository.delete(recipe);

    }

    @Override
    public List<StepOfRecipe> findAllStepsofRecipeByRecipeId(long id) {
        return stepOfRecipeRepository.findAllByRecipeId(id);
    }

    @Override
    public List<Ingredient> findAllIngredientsByRecipeId(long id) {
        return ingredientRepository.findAllByRecipeId(id);
    }

    @Override
    public List<LevelOfDificulty> findAllLevelsOfDificulty() {
        return levelOfDificultyRepository.findAll();
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Recipe getRecipe(long id) {
        return recipeRepository.getRecipeById(id);
    }


}
