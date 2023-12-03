package interface_adapters.generate_recipe;

import api.edamam.GenerateRecipeAPICaller;
import app.GenerateRecipeUseCaseFactory;
import data_access.FileRecipeDataAccessObject;
import entity.Recipe;
import entity.RecipeFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.after_generated_recipe.AfterGeneratedRecipeViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.generate_recipe.GenerateRecipeDataAccessInterface;
import use_case.generate_recipe.GenerateRecipeInputData;

public class GenerateRecipeTest {
    private ViewManagerModel viewManagerModel;
    private AfterGeneratedRecipeViewModel afterGeneratedRecipeViewModel;
    private GenerateRecipeDataAccessInterface recipeDAO;
    private GenerateRecipeController controller;

    @BeforeEach
    void init() {
        viewManagerModel = new ViewManagerModel();
        afterGeneratedRecipeViewModel = new AfterGeneratedRecipeViewModel();
        GenerateRecipeViewModel generateRecipeViewModel = new GenerateRecipeViewModel();
        recipeDAO = new FileRecipeDataAccessObject(new RecipeFactory());
        controller = GenerateRecipeUseCaseFactory.createGenerateUseCase(viewManagerModel, afterGeneratedRecipeViewModel, generateRecipeViewModel, recipeDAO);
    }

    /**
     * Test the use case of generating a recipe (take the expected recipe from afterGeneratedRecipeViewModel set by presenter)
     * Therefore, this test is dependent on the correctness of the presenter
     */
    @Test
    public void testGenerateRecipe() {
        // make the input data
        String q = "chicken";
        String[] diet = {"balanced"};
        String[] health = {"alcohol-free"};
        String[] cuisineType = {"Chinese", "Japanese", "American"};
        String[] mealType = {"Lunch", "Dinner"};
        String minCalories = "100";
        String maxCalories = "1000";
        String maxPreparationTime = "100";

        // execute the use case
        controller.execute(q, diet, health, cuisineType, mealType, minCalories, maxCalories, maxPreparationTime);

        // check that the recipe saved is correct
        Recipe expectedRecipe = afterGeneratedRecipeViewModel.getState().getRecipe();
        Assertions.assertNotNull(expectedRecipe);

        Recipe actualSavedRecipe = recipeDAO.getRecipe(expectedRecipe.getLabel());
        Assertions.assertNotNull(actualSavedRecipe);

        Assertions.assertEquals(expectedRecipe.getLabel(), actualSavedRecipe.getLabel());
        Assertions.assertEquals(expectedRecipe.getRecipeUrl(), actualSavedRecipe.getRecipeUrl());
        Assertions.assertEquals(expectedRecipe.getImagePath(), actualSavedRecipe.getImagePath());
        Assertions.assertEquals(expectedRecipe.getCalories(), actualSavedRecipe.getCalories());
        Assertions.assertEquals(expectedRecipe.getIngredients(), actualSavedRecipe.getIngredients());
        Assertions.assertEquals(expectedRecipe.getPreparationTime(), actualSavedRecipe.getPreparationTime());
        Assertions.assertEquals(expectedRecipe.getYield(), actualSavedRecipe.getYield());

        // check other expected things (from the presenter)
        Assertions.assertEquals(viewManagerModel.getActiveView(), afterGeneratedRecipeViewModel.getViewName());
    }

    @Test
    public void testGenerateRecipeDataAccess() {
        // create the data access object
        GenerateRecipeDataAccessInterface recipeDAO = new FileRecipeDataAccessObject(new RecipeFactory());

        // make the input data
        String q = "chicken";
        String[] diet = {"balanced"};
        String[] health = {"alcohol-free"};
        String[] cuisineType = {"Chinese", "Japanese", "American"};
        String[] mealType = {"Lunch", "Dinner"};
        String minCalories = "100";
        String maxCalories = "1000";
        String maxPreparationTime = "100";

        Recipe generatedRecipe = GenerateRecipeAPICaller.call(new GenerateRecipeInputData(q, diet, health, cuisineType, mealType, minCalories + "-" + maxCalories, "0-" + maxPreparationTime)).getRecipe();
        recipeDAO.save(generatedRecipe);

        // check that the recipe saved is correct
        Recipe actualSavedRecipe = recipeDAO.getRecipe(generatedRecipe.getLabel());

        Assertions.assertNotNull(actualSavedRecipe);
        Assertions.assertEquals(generatedRecipe.getLabel(), actualSavedRecipe.getLabel());
        Assertions.assertEquals(generatedRecipe.getRecipeUrl(), actualSavedRecipe.getRecipeUrl());
        Assertions.assertEquals(generatedRecipe.getImagePath(), actualSavedRecipe.getImagePath());
        Assertions.assertEquals(generatedRecipe.getCalories(), actualSavedRecipe.getCalories());
        Assertions.assertEquals(generatedRecipe.getIngredients(), actualSavedRecipe.getIngredients());
        Assertions.assertEquals(generatedRecipe.getPreparationTime(), actualSavedRecipe.getPreparationTime());
        Assertions.assertEquals(generatedRecipe.getYield(), actualSavedRecipe.getYield());
    }
}
