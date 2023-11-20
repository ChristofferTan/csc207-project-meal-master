package interface_adapters.add_favorite_recipe;

import interface_adapters.ViewManagerModel;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import use_case.add_favorite_recipe.AddFavoriteRecipeOutputBoundary;
import use_case.add_favorite_recipe.AddFavoriteRecipeOutputData;

public class AddFavoriteRecipePresenter implements AddFavoriteRecipeOutputBoundary {
    private final GenerateRecipeViewModel generateRecipeViewModel;
    private ViewManagerModel viewManagerModel;

    public AddFavoriteRecipePresenter(GenerateRecipeViewModel generateRecipeViewModel, ViewManagerModel viewManagerModel) {
        this.generateRecipeViewModel = generateRecipeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddFavoriteRecipeOutputData addFavoriteRecipeOutputData) {
        System.out.println("You have successfully added " + addFavoriteRecipeOutputData.getRecipe().getLabel() + " to you favourite list");
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println(error);
    }
}
