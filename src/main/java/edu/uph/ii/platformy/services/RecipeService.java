package edu.uph.ii.platformy.services;


import edu.uph.ii.platformy.models.*;

import java.util.List;

public interface RecipeService
{
    void saveRecipe(Recipe recipe);
    void saveAllSteps(List<StepOfRecipe> stepOfRecipeList);
    void saveAllIngrediens(List<Ingredient> ingredientList);

    void deleteAllIngredientByRecipeId(long id);
    void deleteAllStepsOfRecipeByRecipeId(long id);
    void deleteRecipe(long id);

    List<StepOfRecipe> findAllStepsofRecipeByRecipeId(long id);
    List<Ingredient> findAllIngredientsByRecipeId(long id);

    List<LevelOfDificulty> findAllLevelsOfDificulty();
    List<Category> findAllCategory();

    Recipe getRecipe(long id);

}
