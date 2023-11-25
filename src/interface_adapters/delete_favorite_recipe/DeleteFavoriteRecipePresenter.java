package interface_adapters.delete_favorite_recipe;

import interface_adapters.ViewManagerModel;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeOutputBoundary;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeOutputData;

public class DeleteFavoriteRecipePresenter implements DeleteFavoriteRecipeOutputBoundary {
    private final GenerateRecipeViewModel generateRecipeViewModel;
    private final DeleteFavoriteRecipeViewModel deleteFavoriteRecipeViewModel;
    private ViewManagerModel viewManagerModel;

    public DeleteFavoriteRecipePresenter(GenerateRecipeViewModel generateRecipeViewModel, DeleteFavoriteRecipeViewModel deleteFavoriteRecipeViewModel, ViewManagerModel viewManagerModel) {
        this.generateRecipeViewModel = generateRecipeViewModel;
        this.deleteFavoriteRecipeViewModel = deleteFavoriteRecipeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DeleteFavoriteRecipeOutputData deleteFavoriteRecipeOutputData) {
        System.out.println("You have successfully deleted " + deleteFavoriteRecipeOutputData.getLabel());
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("Error");
    }
}
