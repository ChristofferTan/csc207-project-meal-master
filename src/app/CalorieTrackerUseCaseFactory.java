package app;

import interface_adapters.ViewManagerModel;
import interface_adapters.calorie_tracker.CalorieTrackerController;
import interface_adapters.calorie_tracker.CalorieTrackerPresenter;
import interface_adapters.my_planner.MyPlannerViewModel;
import use_case.calorie_tracker.CalorieTrackerInputBoundary;
import use_case.calorie_tracker.CalorieTrackerInteractor;
import use_case.calorie_tracker.CalorieTrackerOutputBoundary;

public class CalorieTrackerUseCaseFactory {
    private CalorieTrackerUseCaseFactory() {}

    public static CalorieTrackerController createCalorieTrackerUseCase(
            ViewManagerModel viewManagerModel,
            MyPlannerViewModel myPlannerViewModel) {
        CalorieTrackerOutputBoundary calorieTrackerOutputBoundary = new CalorieTrackerPresenter(viewManagerModel, myPlannerViewModel);

        CalorieTrackerInputBoundary calorieTrackerInteractor = new CalorieTrackerInteractor(null, calorieTrackerOutputBoundary);
        return new CalorieTrackerController(calorieTrackerInteractor);
    }
}
