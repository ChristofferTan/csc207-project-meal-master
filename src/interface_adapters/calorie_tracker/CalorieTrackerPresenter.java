package interface_adapters.calorie_tracker;

import use_case.calorie_tracker.CalorieTrackerOutputBoundary;
import use_case.calorie_tracker.CalorieTrackerOutputData;

public class CalorieTrackerPresenter implements CalorieTrackerOutputBoundary {
    public CalorieTrackerPresenter() {
    }

    @Override
    public void prepareSuccessView(CalorieTrackerOutputData calorieTrackerOutputData) {
        float weeklyCalories = calorieTrackerOutputData.getWeeklyCalories();
        float averageDailyCalories = calorieTrackerOutputData.getAverageDailyCalories();

        System.out.println("Weekly Calories: " + weeklyCalories);
        System.out.println("Average Daily Calories: " + averageDailyCalories);
    }
}
