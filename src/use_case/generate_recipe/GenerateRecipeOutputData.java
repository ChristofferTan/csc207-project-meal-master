package use_case.generate_recipe;

import entity.Recipe;

public class GenerateRecipeOutputData {
    private final Recipe recipe;

    public GenerateRecipeOutputData(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
