package use_case.after_generated_recipe;

import entity.Recipe;

public class AfterGeneratedRecipeInputData {
    final private String username;
    final private Recipe recipe;

    public AfterGeneratedRecipeInputData(String username, Recipe recipe) {
        this.username = username;
        this.recipe = recipe;
    }

    public String getUsername() {
        return username;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
