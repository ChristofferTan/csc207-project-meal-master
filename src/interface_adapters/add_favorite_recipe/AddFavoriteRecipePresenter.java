package interface_adapters.add_favorite_recipe;

import interface_adapters.ViewManagerModel;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import use_case.add_favorite_recipe.AddFavoriteRecipeOutputBoundary;
import use_case.add_favorite_recipe.AddFavoriteRecipeOutputData;

public class AddFavoriteRecipePresenter implements AddFavoriteRecipeOutputBoundary {
    private final GenerateRecipeViewModel generateRecipeViewModel;
    private final AddFavoriteRecipeViewModel addFavoriteRecipeViewModel;
    private ViewManagerModel viewManagerModel;

    public AddFavoriteRecipePresenter(GenerateRecipeViewModel generateRecipeViewModel, AddFavoriteRecipeViewModel addFavoriteRecipeViewModel, ViewManagerModel viewManagerModel) {
        this.generateRecipeViewModel = generateRecipeViewModel;
        this.addFavoriteRecipeViewModel = addFavoriteRecipeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddFavoriteRecipeOutputData addFavoriteRecipeOutputData) {
        System.out.println("You have successfully added " + addFavoriteRecipeOutputData.getLabel() + " to your favourite list");
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println(error);
    }
}
