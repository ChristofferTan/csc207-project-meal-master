package interface_adapters.save_recipe;

import entity.MealType;
import entity.Recipe;
import use_case.save_recipe.SaveRecipeInputBoundary;
import use_case.save_recipe.SaveRecipeInputData;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class SaveRecipeController {
    final SaveRecipeInputBoundary saveRecipeInteractor;

    public SaveRecipeController(SaveRecipeInputBoundary saveRecipeInteractor) {
        this.saveRecipeInteractor = saveRecipeInteractor;
    }

    public void execute(String username, DayOfWeek day, MealType mealType, Recipe recipe) {
        SaveRecipeInputData saveRecipeInputData = new SaveRecipeInputData(username, day, mealType, recipe);

        saveRecipeInteractor.execute(saveRecipeInputData);
    }
}