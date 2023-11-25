package use_case.generate_recipe;

import entity.Recipe;

public interface GenerateRecipeDataAccessInterface {
    public void save(Recipe recipe);
}
