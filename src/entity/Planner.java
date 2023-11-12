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
        for (DayOfWeek day : DayOfWeek.values()) {
            this.weeklyRecipes.put(day, new HashMap<>());
        }
    }

    public HashMap<DayOfWeek, HashMap<MealType, Recipe>> getWeeklyRecipes() {
        return weeklyRecipes;
    }

    public HashMap<MealType, Recipe> getRecipesByDay(DayOfWeek day) {
        return weeklyRecipes.get(day);
    }

    public String getUsername() {
        return username;
    }
}
