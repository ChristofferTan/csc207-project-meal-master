package use_case.add_favorite_recipe;

import data_access.FileRecipeDataAccessObject;

public class AddFavoriteRecipeInteractor implements AddFavoriteRecipeInputBoundary{
    final AddFavoriteRecipeUserDataAccessInterface userDataAccessObject;
    final FileRecipeDataAccessObject fileRecipeDataAccessObject;
    final AddFavoriteRecipeOutputBoundary presenter;

    public AddFavoriteRecipeInteractor(AddFavoriteRecipeUserDataAccessInterface userDataAccessObject, FileRecipeDataAccessObject fileRecipeDataAccessObject, AddFavoriteRecipeOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.fileRecipeDataAccessObject = fileRecipeDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(AddFavoriteRecipeInputData addFavoriteRecipeInputData) {
        if (userDataAccessObject.isExists(addFavoriteRecipeInputData.getUsername(), addFavoriteRecipeInputData.getLabel())) {
            presenter.prepareFailView("Sorry, this recipe has already been in your list");
        }
        else {
            userDataAccessObject.saveFavoriteRecipe(addFavoriteRecipeInputData.getUsername(), addFavoriteRecipeInputData.getLabel());

            AddFavoriteRecipeOutputData addFavoriteRecipeOutputData = new AddFavoriteRecipeOutputData(addFavoriteRecipeInputData.getLabel(), fileRecipeDataAccessObject.getRecipe(addFavoriteRecipeInputData.getLabel()).getRecipeUrl());
            presenter.prepareSuccessView(addFavoriteRecipeOutputData);
        }
    }
}
