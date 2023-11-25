package use_case.delete_favorite_recipe;

public interface DeleteFavoriteRecipeOutputBoundary {
    void prepareSuccessView(DeleteFavoriteRecipeOutputData deleteFavoriteRecipeOutputData);
    void prepareFailView(String error);
}
