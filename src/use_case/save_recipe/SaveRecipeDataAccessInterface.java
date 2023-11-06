package use_case.save_recipe;

import entity.MealType;
import entity.Recipe;

import java.time.DayOfWeek;

public interface SaveRecipeDataAccessInterface {
    void save(DayOfWeek day, MealType mealType, Recipe recipe);
    Recipe get(DayOfWeek day, MealType mealType);
}
