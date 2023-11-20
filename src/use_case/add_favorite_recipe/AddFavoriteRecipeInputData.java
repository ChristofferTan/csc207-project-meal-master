package use_case.add_favorite_recipe;

import entity.Recipe;

public class AddFavoriteRecipeInputData {
    final private String username;
    final private Recipe recipe;

    public AddFavoriteRecipeInputData(String username, Recipe recipe) {
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
