package interface_adapters.save_recipe;

import use_case.save_recipe.SaveRecipeInputBoundary;
import use_case.save_recipe.SaveRecipeInputData;

public class SaveRecipeController {
    final SaveRecipeInputBoundary saveRecipeInteractor;

    public SaveRecipeController(SaveRecipeInputBoundary saveRecipeInteractor) {
        this.saveRecipeInteractor = saveRecipeInteractor;
    }

    public void execute() {
        SaveRecipeInputData saveRecipeInputData = new SaveRecipeInputData();

        saveRecipeInteractor.execute(saveRecipeInputData);
    }
}
