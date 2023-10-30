package api.edamam;

import entity.Recipe;

public class GenerateRecipeAPIData {
    private final Recipe recipe;

    public GenerateRecipeAPIData(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
