package interface_adapters.save_recipe;

import use_case.save_recipe.SaveRecipeOutputData;
import use_case.save_recipe.SaveRecipeOutputBoundary;

public class SaveRecipePresenter implements SaveRecipeOutputBoundary{
    public SaveRecipePresenter() {}

    public void prepareSuccessView(SaveRecipeOutputData saveRecipeOutputData) {
        System.out.println("You have added the recipe to the planner successfully");
    }
}
