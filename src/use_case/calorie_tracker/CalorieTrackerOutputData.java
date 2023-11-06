package use_case.calorie_tracker;

import entity.Planner;
import entity.Recipe;

import java.util.ArrayList;

public class CalorieTrackerOutputData {
    private final Planner planner;

    public CalorieTrackerOutputData(Planner planner) {
        this.planner = planner;
    }

    public float getWeeklyCalories() {
        float calories = 0;
        for (ArrayList<Recipe> recipes: planner.getPlanner().values()) {
            for (Recipe recipe: recipes) {
                calories += recipe.getCalories();
            }
        }
        return calories;
    }

    public float getAverageDailyCalories() {
        float calories = 0;
        for (ArrayList<Recipe> recipes: planner.getPlanner().values()) {
            for (Recipe recipe: recipes) {
                calories += recipe.getCalories();
            }
        }
        return calories / 7;
    }
}
