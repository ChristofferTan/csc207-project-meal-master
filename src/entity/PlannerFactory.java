package entity;

import java.time.DayOfWeek;
import java.util.HashMap;

public class PlannerFactory {
    public Planner create(String username) {
        Planner planner = new Planner(username);
        HashMap<DayOfWeek, HashMap<MealType, Recipe>> weeklyRecipes = planner.getWeeklyRecipes();
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            weeklyRecipes.put(dayOfWeek, new HashMap<>());
            for (MealType mealType: MealType.values()) {
                weeklyRecipes.get(dayOfWeek).put(mealType, null);
            }
        }
        return planner;
    }
}
