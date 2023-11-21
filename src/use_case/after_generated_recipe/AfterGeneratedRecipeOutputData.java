package use_case.after_generated_recipe;

import entity.Recipe;

import java.util.ArrayList;

public class AfterGeneratedRecipeOutputData {
    private final String username;
    private final Recipe recipe;

    public AfterGeneratedRecipeOutputData(String username, Recipe recipe) {
        this.username = username;
        this.recipe = recipe;
    }

    public String getUsername() {
        return username;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getLabel() {
        return recipe.getLabel();
    }

    public String getImagePath() {
        return recipe.getImagePath();
    }

    public int getCalories() {
        return recipe.getCalories();
    }

    public int getYield() {
        return recipe.getYield();
    }

    public ArrayList<String> getIngredientLines() {
        return recipe.getIngredients();
    }

    public int getPreparationTime() {
        return recipe.getPreparationTime();
    }

    public String getRecipeUrl() {
        return recipe.getRecipeUrl();
    }
}
