package use_case.calorie_tracker;

import entity.MealType;
import entity.Planner;
import entity.Recipe;

import java.util.HashMap;

public class CalorieTrackerInteractor implements CalorieTrackerInputBoundary{
    final CalorieTrackerDataAccessInterface dataAccessInterface;
    final CalorieTrackerOutputBoundary calorieTrackerPresenter;

    public CalorieTrackerInteractor(CalorieTrackerDataAccessInterface dataAccessInterface,
                                    CalorieTrackerOutputBoundary calorieTrackerOutputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.calorieTrackerPresenter = calorieTrackerOutputBoundary;
    }

    @Override
    public void execute(CalorieTrackerInputData calorieTrackerInputData) {
        try {
            Planner planner = calorieTrackerInputData.getPlanner();
            int weeklyCalories = calculateWeeklyCalories(planner);
            int averageDailyCalories = calculateAverageDailyCalories(planner);

            CalorieTrackerOutputData outputData = new CalorieTrackerOutputData(planner, weeklyCalories, averageDailyCalories);
        } catch (Exception e) {
            calorieTrackerPresenter.prepareFailView(e.getMessage());
        }
    }

    private int calculateWeeklyCalories(Planner planner) {
        float calories = 0;
        for (HashMap<MealType, Recipe> recipes: planner.getWeeklyRecipes().values()) {
            for (Recipe recipe: recipes.values()) {
                calories += recipe.getCalories();
            }
        }
        return Math.round(calories);
    }

    private int calculateAverageDailyCalories(Planner planner) {
        float calories = 0;
        for (HashMap<MealType, Recipe> recipes: planner.getWeeklyRecipes().values()) {
            for (Recipe recipe: recipes.values()) {
                calories += recipe.getCalories();
            }
        }
        return Math.round(calories / 7);
    }
}
