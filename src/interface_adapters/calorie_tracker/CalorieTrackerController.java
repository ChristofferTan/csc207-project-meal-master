package interface_adapters.calorie_tracker;

import entity.Planner;
import use_case.calorie_tracker.CalorieTrackerInputBoundary;
import use_case.calorie_tracker.CalorieTrackerInputData;

public class CalorieTrackerController {
    final CalorieTrackerInputBoundary calorieTrackerInteractor;

    public CalorieTrackerController(CalorieTrackerInputBoundary calorieTrackerInteractor) {
        this.calorieTrackerInteractor = calorieTrackerInteractor;
    }

    public void execute(Planner planner) {
        CalorieTrackerInputData calorieTrackerInputData = new CalorieTrackerInputData(planner);
        calorieTrackerInteractor.execute(calorieTrackerInputData);
    }
}
