package entity;

import java.time.DayOfWeek;
import java.util.HashMap;

public class Planner {
    private final HashMap<DayOfWeek, HashMap<MealType, Recipe>> weeklyRecipes;

    public Planner() {
        this.weeklyRecipes = new HashMap<>();
        weeklyRecipes.put(DayOfWeek.MONDAY, new HashMap<>());
        weeklyRecipes.put(DayOfWeek.TUESDAY, new HashMap<>());
        weeklyRecipes.put(DayOfWeek.WEDNESDAY, new HashMap<>());
        weeklyRecipes.put(DayOfWeek.THURSDAY, new HashMap<>());
        weeklyRecipes.put(DayOfWeek.FRIDAY, new HashMap<>());
        weeklyRecipes.put(DayOfWeek.SATURDAY, new HashMap<>());
        weeklyRecipes.put(DayOfWeek.SUNDAY, new HashMap<>());
    }

    public HashMap<DayOfWeek, HashMap<MealType, Recipe>> getWeeklyRecipes() {
        return weeklyRecipes;
    }

    public HashMap<MealType, Recipe> getRecipesByDay(DayOfWeek day) {
        return weeklyRecipes.get(day);
    }
}
