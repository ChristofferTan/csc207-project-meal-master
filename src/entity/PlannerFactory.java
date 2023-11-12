package entity;

import java.time.DayOfWeek;
import java.util.HashMap;

public class PlannerFactory {
    public Planner create(String username) {
        Planner planner = new Planner(username);
        HashMap<DayOfWeek, HashMap<MealType, Recipe>> weeklyRecipes = planner.getWeeklyRecipes();
        weeklyRecipes.put(DayOfWeek.MONDAY, new HashMap<>());
        weeklyRecipes.put(DayOfWeek.TUESDAY, new HashMap<>());
        weeklyRecipes.put(DayOfWeek.WEDNESDAY, new HashMap<>());
        weeklyRecipes.put(DayOfWeek.THURSDAY, new HashMap<>());
        weeklyRecipes.put(DayOfWeek.FRIDAY, new HashMap<>());
        weeklyRecipes.put(DayOfWeek.SATURDAY, new HashMap<>());
        weeklyRecipes.put(DayOfWeek.SUNDAY, new HashMap<>());
        return planner;
    }
}
