package interface_adapters.save_recipe;

import use_case.save_recipe.SaveRecipeOutputBoundary;
import use_case.save_recipe.SaveRecipeOutputData;

public class SaveRecipePresenter implements SaveRecipeOutputBoundary {
    public SaveRecipePresenter() {
    }

    @Override
    public void prepareSuccessView(SaveRecipeOutputData recipe) {
        System.out.println("You have successfully saved the recipe.");
    }
}