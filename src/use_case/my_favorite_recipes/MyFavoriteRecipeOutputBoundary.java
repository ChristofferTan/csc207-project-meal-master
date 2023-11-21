package use_case.my_favorite_recipes;

public interface MyFavoriteRecipeOutputBoundary {
    void prepareSuccessView(MyFavoriteRecipeOutputData myFavoriteRecipeOutputData);
    void prepareFailView(String error);
}
