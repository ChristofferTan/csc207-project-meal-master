package entity;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class Planner {
    private final String username;
    private final HashMap<DayOfWeek, HashMap<MealType, Recipe>> weeklyRecipes;

    public Planner(String username) {
        this.username = username;
        this.weeklyRecipes = new HashMap<>();
    }

    public HashMap<DayOfWeek, HashMap<MealType, Recipe>> getWeeklyRecipes() {
        return weeklyRecipes;
    }

    public HashMap<MealType, Recipe> getRecipesByDay(DayOfWeek day) {
        return weeklyRecipes.get(day);
    }

    public Recipe get(DayOfWeek day, MealType mealType) {
        if (isExistsByDayAndMealType(day, mealType)) {
            return weeklyRecipes.get(day).get(mealType);
        } else {
            return null;
        }
    }

    private boolean isExistsByDayAndMealType(DayOfWeek day, MealType mealType) {
        return weeklyRecipes.containsKey(day) && weeklyRecipes.get(day).containsKey(mealType);
    }

    public String getUsername() {
        return username;
    }
}
