package use_case.add_favorite_recipe;

public interface AddFavoriteRecipeOutputBoundary {
    void prepareSuccessView(AddFavoriteRecipeOutputData addFavoriteRecipeOutputData);
    void prepareFailView(String error);
}
