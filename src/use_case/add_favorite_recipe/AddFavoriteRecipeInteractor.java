package use_case.add_favorite_recipe;

import data_access.FileRecipeDataAccessObject;
import entity.Recipe;

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
        Recipe recipe = fileRecipeDataAccessObject.getRecipe(addFavoriteRecipeInputData.getLabel());
        if (userDataAccessObject.isExists(addFavoriteRecipeInputData.getUsername(), recipe)) {
            presenter.prepareFailView("Sorry, this recipe has already been in your list");
        }
        else {
            userDataAccessObject.saveFavoriteRecipe(addFavoriteRecipeInputData.getUsername(), recipe);

            AddFavoriteRecipeOutputData addFavoriteRecipeOutputData = new AddFavoriteRecipeOutputData(recipe);
            presenter.prepareSuccessView(addFavoriteRecipeOutputData);
        }
    }
}
