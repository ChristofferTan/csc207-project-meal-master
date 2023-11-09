package interface_adapters.generate_recipe;

import entity.Ingredient;
import entity.Recipe;
import interface_adapters.ViewManagerModel;
import use_case.generate_recipe.GenerateRecipeOutputBoundary;
import use_case.generate_recipe.GenerateRecipeOutputData;

import java.util.ArrayList;

public class GenerateRecipePresenter implements GenerateRecipeOutputBoundary {
    private final GenerateRecipeViewModel generateRecipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public GenerateRecipePresenter(GenerateRecipeViewModel generateRecipeViewModel, ViewManagerModel viewManagerModel) {
        this.generateRecipeViewModel = generateRecipeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GenerateRecipeOutputData generateRecipeOutputData) {
        Recipe recipe = generateRecipeOutputData.getRecipe();
        String label = recipe.getLabel();
        String recipeUrl = recipe.getRecipeUrl();
        String imagePath = recipe.getImagePath();
        int calories = recipe.getCalories();
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        int preparationTime = recipe.getPreparationTime();
        int yield = recipe.getYield();

        System.out.println("Label: " + label);
        System.out.println("Image URL: " + imagePath);
        System.out.println("Number of serving(s): " + yield);
        System.out.println("Calories (per serving): " + calories);
        System.out.println("Ingredients:");
        for (Ingredient ingredient : ingredients) {
            System.out.println('\t' + ingredient.getText());
        }
        System.out.println("Recipe URL: " + recipeUrl);
        System.out.println("Preparation Time: " + preparationTime);
    }
}
