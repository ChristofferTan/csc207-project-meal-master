package interface_adapters.generate_recipe;

import use_case.generate_recipe.GenerateRecipeOutputBoundary;
import use_case.generate_recipe.GenerateRecipeOutputData;

public class GenerateRecipePresenter implements GenerateRecipeOutputBoundary {
    public GenerateRecipePresenter() {
    }

    @Override
    public void prepareSuccessView(GenerateRecipeOutputData recipe) {
        String label = recipe.getLabel();
        int calories = recipe.getCalories();
        String[] ingredients = recipe.getIngredients();
        String recipeUrl = recipe.getRecipeUrl();
        int preparationTime = recipe.getPreparationTime();
        int yield = recipe.getYield();

        System.out.println("Label: " + label);
        System.out.println("Number of serving(s): " + yield);
        System.out.println("Calories (per serving): " + calories);
        System.out.println("Ingredients:");
        for (String ingredient : ingredients) {
            System.out.println('\t' + ingredient);
        }
        System.out.println("Recipe URL: " + recipeUrl);
        System.out.println("Preparation Time: " + preparationTime);
    }
}
