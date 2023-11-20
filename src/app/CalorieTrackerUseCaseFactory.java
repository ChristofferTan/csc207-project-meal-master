package app;

import interface_adapters.calorie_tracker.CalorieTrackerController;
import interface_adapters.calorie_tracker.CalorieTrackerPresenter;
import use_case.calorie_tracker.CalorieTrackerInputBoundary;
import use_case.calorie_tracker.CalorieTrackerInteractor;
import use_case.calorie_tracker.CalorieTrackerOutputBoundary;

public class CalorieTrackerUseCaseFactory {
    private CalorieTrackerUseCaseFactory() {}

    public static CalorieTrackerController createCalorieTrackerUseCase() {
        CalorieTrackerOutputBoundary calorieTrackerOutputBoundary = new CalorieTrackerPresenter();

        CalorieTrackerInputBoundary calorieTrackerInteractor = new CalorieTrackerInteractor(null, calorieTrackerOutputBoundary);
        return new CalorieTrackerController(calorieTrackerInteractor);
    }
}
