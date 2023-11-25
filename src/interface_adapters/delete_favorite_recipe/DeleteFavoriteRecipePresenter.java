package interface_adapters.delete_favorite_recipe;

import interface_adapters.ViewManagerModel;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeState;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeOutputBoundary;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeOutputData;

public class DeleteFavoriteRecipePresenter implements DeleteFavoriteRecipeOutputBoundary {
    private final DeleteFavoriteRecipeViewModel deleteFavoriteRecipeViewModel;
    private ViewManagerModel viewManagerModel;

    public DeleteFavoriteRecipePresenter(ViewManagerModel viewManagerModel, DeleteFavoriteRecipeViewModel deleteFavoriteRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.deleteFavoriteRecipeViewModel = deleteFavoriteRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(DeleteFavoriteRecipeOutputData response) {
        DeleteFavoriteRecipeState deleteFavoriteRecipeState = deleteFavoriteRecipeViewModel.getState();
        deleteFavoriteRecipeState.setLabel(response.getLabel());
        this.deleteFavoriteRecipeViewModel.setState(deleteFavoriteRecipeState);
        this.deleteFavoriteRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(deleteFavoriteRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println(error);
    }
}
