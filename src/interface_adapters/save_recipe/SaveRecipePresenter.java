package interface_adapters.save_recipe;

import interface_adapters.ViewManagerModel;
import use_case.save_recipe.SaveRecipeOutputData;
import use_case.save_recipe.SaveRecipeOutputBoundary;

public class SaveRecipePresenter implements SaveRecipeOutputBoundary {
    private final SaveRecipeViewModel saveRecipeViewModel;
    private ViewManagerModel viewManagerModel;
    public SaveRecipePresenter(ViewManagerModel viewManagerModel,
                               SaveRecipeViewModel saveRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.saveRecipeViewModel = saveRecipeViewModel;
    }

    public void prepareSuccessView(SaveRecipeOutputData saveRecipeOutputData) {
        System.out.println(saveRecipeOutputData.getUsername() + " have added the " +  saveRecipeOutputData.getRecipe().getLabel() + " to the planner successfully");
    }
}
