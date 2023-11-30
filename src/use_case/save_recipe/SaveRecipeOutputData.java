package use_case.save_recipe;

import entity.Recipe;

public class SaveRecipeOutputData{
    final private String username;
    final private Recipe recipe;
    public SaveRecipeOutputData(String username, Recipe recipe) {
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
