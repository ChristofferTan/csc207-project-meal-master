package interface_adapters.add_favorite_recipe;

import entity.Recipe;
import use_case.add_favorite_recipe.AddFavoriteRecipeInputBoundary;
import use_case.add_favorite_recipe.AddFavoriteRecipeInputData;

public class AddFavoriteRecipeController {
    final AddFavoriteRecipeInputBoundary addFavoriteRecipeInteractor;

    public AddFavoriteRecipeController(AddFavoriteRecipeInputBoundary addFavoriteRecipeInteractor) {
        this.addFavoriteRecipeInteractor = addFavoriteRecipeInteractor;
    }
    public void execute(String username, String label) {
        AddFavoriteRecipeInputData addFavoriteRecipeInputData = new AddFavoriteRecipeInputData(username, label);
        addFavoriteRecipeInteractor.execute(addFavoriteRecipeInputData);
    }
}
