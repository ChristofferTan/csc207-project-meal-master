package data_access;

import entity.MealType;
import entity.Planner;
import entity.Recipe;
import use_case.save_recipe.SaveRecipeDataAccessInterface;

import java.time.DayOfWeek;

public class InMemoryPlannerDataAccessObject implements SaveRecipeDataAccessInterface {
    private final Planner planner = new Planner();

    @Override
    public void save(DayOfWeek day, MealType mealType, Recipe recipe) {
        planner.getRecipesByDay(day).put(mealType, recipe);
    }

    @Override
    public Recipe get(DayOfWeek day, MealType mealType) {
        return planner.getRecipesByDay(day).get(mealType);
    }
}
