package interface_adapter;

import use_case.GenerateRecipeOutputBoundary;
import use_case.GenerateRecipeOutputData;

public class GenerateRecipePresenter implements GenerateRecipeOutputBoundary {
    public GenerateRecipePresenter() {
    }

    @Override
    public void prepareSuccessView(GenerateRecipeOutputData recipe) {
        String label = recipe.getLabel();
        int calories = recipe.getCalories();
        String[] ingredients = recipe.getIngredients();
        String recipeUrl = recipe.getRecipeUrl();

        System.out.println("Label: " + label);
        System.out.println("Calories: " + calories);
        System.out.println("Ingredients:");
        for (String ingredient : ingredients) {
            System.out.println(ingredient);
        }
        System.out.println("Recipe URL: " + recipeUrl);
    }
}
