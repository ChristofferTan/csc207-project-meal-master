package use_case.save_recipe;

import data_access.FilePlannerDataAccessObject;
import entity.MealType;
import entity.Recipe;

import java.time.DayOfWeek;

public interface SaveRecipeDataAccessInterface {
    void save(String username, DayOfWeek day, MealType mealType, Recipe recipe);
}
