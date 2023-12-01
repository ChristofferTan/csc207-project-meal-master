package use_case.calorie_tracker;

import entity.MealType;
import entity.Planner;
import entity.Recipe;

import java.util.ArrayList;
import java.util.HashMap;

public class CalorieTrackerOutputData {
    private final Planner planner;
    private final int weeklyCalories;
    private final int averageDailyCalories;

    public CalorieTrackerOutputData(Planner planner, int weeklyCalories, int averageDailyCalories) {
        this.planner = planner;
        this.weeklyCalories = weeklyCalories;
        this.averageDailyCalories = averageDailyCalories;
    }

    public int getWeeklyCalories() {
        return weeklyCalories;
    }

    public int getAverageDailyCalories() {
        return averageDailyCalories;
    }

    public Planner getPlanner() {
        return planner;
    }
}
