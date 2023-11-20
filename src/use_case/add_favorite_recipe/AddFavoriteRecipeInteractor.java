package use_case.add_favorite_recipe;

public class AddFavoriteRecipeInteractor implements AddFavoriteRecipeInputBoundary{
    final AddFavoriteRecipeUserDataAccessInterface userDataAccessObject;
    final AddFavoriteRecipeOutputBoundary presenter;

    public AddFavoriteRecipeInteractor(AddFavoriteRecipeUserDataAccessInterface userDataAccessObject, AddFavoriteRecipeOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(AddFavoriteRecipeInputData addFavoriteRecipeInputData) {
        if (userDataAccessObject.isExists(addFavoriteRecipeInputData.getUsername(), addFavoriteRecipeInputData.getRecipe())) {
            presenter.prepareFailView("Sorry, this recipe has already been in your list");
        }
        else {
            userDataAccessObject.saveFavoriteRecipe(addFavoriteRecipeInputData.getUsername(), addFavoriteRecipeInputData.getRecipe());

            AddFavoriteRecipeOutputData addFavoriteRecipeOutputData = new AddFavoriteRecipeOutputData(addFavoriteRecipeInputData.getRecipe());
            presenter.prepareSuccessView(addFavoriteRecipeOutputData);
        }
    }
}
