package use_case.add_favorite_recipe;

import entity.Recipe;

public class AddFavoriteRecipeOutputData {
    private final Recipe recipe;

    public AddFavoriteRecipeOutputData(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
