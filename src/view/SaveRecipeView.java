package view;

import interface_adapters.save_recipe.SaveRecipeController;
import interface_adapters.save_recipe.SaveRecipeViewModel;

public class SaveRecipeView {
    public final String viewName = "save recipe";

    private final SaveRecipeViewModel saveRecipeViewModel;
    private final SaveRecipeController saveRecipeController;

    public SaveRecipeView(SaveRecipeController saveRecipeController, SaveRecipeViewModel saveRecipeViewModel) {
        this.saveRecipeController = saveRecipeController;
        this.saveRecipeViewModel = saveRecipeViewModel;
    }

    public SaveRecipeController getSaveRecipeController() {
        return saveRecipeController;
    }
}
