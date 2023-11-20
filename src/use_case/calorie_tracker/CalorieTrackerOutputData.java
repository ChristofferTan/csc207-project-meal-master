package use_case.calorie_tracker;

import entity.MealType;
import entity.Planner;
import entity.Recipe;

import java.util.ArrayList;
import java.util.HashMap;

public class CalorieTrackerOutputData {
    private final Planner planner;

    public CalorieTrackerOutputData(Planner planner) {
        this.planner = planner;
    }

    public int getWeeklyCalories() {
        float calories = 0;
        for (HashMap<MealType, Recipe> recipes: planner.getWeeklyRecipes().values()) {
            for (Recipe recipe: recipes.values()) {
                calories += recipe.getCalories();
            }
        }
        return Math.round(calories);
    }

    public int getAverageDailyCalories() {
        float calories = 0;
        for (HashMap<MealType, Recipe> recipes: planner.getWeeklyRecipes().values()) {
            for (Recipe recipe: recipes.values()) {
                calories += recipe.getCalories();
            }
        }
        return Math.round(calories / 7);
    }
}
