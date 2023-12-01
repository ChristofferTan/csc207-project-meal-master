package interface_adapters.delete_favorite_recipe;

import interface_adapters.ViewManagerModel;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeState;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeViewModel;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeOutputBoundary;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeOutputData;

import javax.swing.*;

public class DeleteFavoriteRecipePresenter implements DeleteFavoriteRecipeOutputBoundary {
    private final MyFavoriteRecipeViewModel myFavoriteRecipeViewModel;

    public DeleteFavoriteRecipePresenter(MyFavoriteRecipeViewModel myFavoriteRecipeViewModel) {
        this.myFavoriteRecipeViewModel = myFavoriteRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(DeleteFavoriteRecipeOutputData response) {
        MyFavoriteRecipeState myFavoriteRecipeState = myFavoriteRecipeViewModel.getState();
        myFavoriteRecipeState.setLabelError(null);
        this.myFavoriteRecipeViewModel.setState(myFavoriteRecipeState);
        this.myFavoriteRecipeViewModel.firePropertyChanged();
        JOptionPane.showConfirmDialog(null, response.getUsername() + " have deleted " +  response.getLabel() + " from my favorite recipes successfully", "Delete succeeded", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void prepareFailView(String error) {
        MyFavoriteRecipeState myFavoriteRecipeState = myFavoriteRecipeViewModel.getState();
        myFavoriteRecipeState.setLabelError(error);
        myFavoriteRecipeViewModel.firePropertyChanged();
    }
}
