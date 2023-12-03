package interface_adapters.calorie_tracker;

import interface_adapters.ViewManagerModel;
import interface_adapters.my_planner.MyPlannerState;
import interface_adapters.my_planner.MyPlannerViewModel;
import use_case.calorie_tracker.CalorieTrackerOutputBoundary;
import use_case.calorie_tracker.CalorieTrackerOutputData;

public class CalorieTrackerPresenter implements CalorieTrackerOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final MyPlannerViewModel myPlannerViewModel;
    public CalorieTrackerPresenter(ViewManagerModel viewManagerModel,
                                   MyPlannerViewModel myPlannerViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.myPlannerViewModel = myPlannerViewModel;
    }

    @Override
    public void prepareSuccessView(CalorieTrackerOutputData calorieTrackerOutputData) {
        int weeklyCalories = calorieTrackerOutputData.getWeeklyCalories();
        int averageDailyCalories = calorieTrackerOutputData.getAverageDailyCalories();

        MyPlannerState state = myPlannerViewModel.getState();
        state.setWeeklyCalories(weeklyCalories);
        state.setAverageMealCalories(averageDailyCalories);
//        myPlannerViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(myPlannerViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        MyPlannerState myPlannerState = myPlannerViewModel.getState();
        myPlannerState.setNoCalorieTrackerError(error);
//        myPlannerViewModel.firePropertyChanged();
    }
}
