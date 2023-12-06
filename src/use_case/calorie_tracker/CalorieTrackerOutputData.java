package use_case.calorie_tracker;

import entity.Planner;

public class CalorieTrackerOutputData {
    private final Planner planner;
    private final int weeklyCalories;
    private final int averageMealCalories;

    public CalorieTrackerOutputData(Planner planner, int weeklyCalories, int averageMealCalories) {
        this.planner = planner;
        this.weeklyCalories = weeklyCalories;
        this.averageMealCalories = averageMealCalories;
    }

    public int getWeeklyCalories() {
        return weeklyCalories;
    }

    public int getAverageMealCalories() {
        return averageMealCalories;
    }

    public Planner getPlanner() {
        return planner;
    }
}
