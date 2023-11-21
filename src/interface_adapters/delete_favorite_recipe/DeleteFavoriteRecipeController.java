package interface_adapters.delete_favorite_recipe;

import use_case.delete_favorite_recipe.DeleteFavoriteRecipeInputBoundary;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeInputData;

public class DeleteFavoriteRecipeController {
    final DeleteFavoriteRecipeInputBoundary deleteFavoriteRecipeInteractor;

    public DeleteFavoriteRecipeController(DeleteFavoriteRecipeInputBoundary deleteFavoriteRecipeInteractor) {
        this.deleteFavoriteRecipeInteractor = deleteFavoriteRecipeInteractor;
    }
    public void execute(String username, String label) {
        DeleteFavoriteRecipeInputData deleteFavoriteRecipeInputData = new DeleteFavoriteRecipeInputData(username, label);
        deleteFavoriteRecipeInteractor.execute(deleteFavoriteRecipeInputData);
    }
}
