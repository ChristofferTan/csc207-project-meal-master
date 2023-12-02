package app;

import interface_adapters.ViewManagerModel;
import interface_adapters.calorie_tracker.CalorieTrackerController;
import interface_adapters.calorie_tracker.CalorieTrackerPresenter;
import interface_adapters.my_planner.MyPlannerController;
import interface_adapters.my_planner.MyPlannerPresenter;
import interface_adapters.my_planner.MyPlannerViewModel;
import use_case.calorie_tracker.CalorieTrackerInputBoundary;
import use_case.calorie_tracker.CalorieTrackerInteractor;
import use_case.calorie_tracker.CalorieTrackerOutputBoundary;
import use_case.my_planner.MyPlannerDataAccessInterface;
import use_case.my_planner.MyPlannerInputBoundary;
import use_case.my_planner.MyPlannerInteractor;
import use_case.my_planner.MyPlannerOutputBoundary;
import view.MyPlannerView;

public class MyPlannerUseCaseFactory {

    private MyPlannerUseCaseFactory() {}; // Prevent instantiation

    public static MyPlannerView create(
            ViewManagerModel viewManagerModel,
            MyPlannerViewModel myPlannerViewModel,
            MyPlannerDataAccessInterface myPlannerDataAccessObject) {
        MyPlannerController myPlannerController = createMyPlannerUseCase(
                viewManagerModel,
                myPlannerViewModel,
                myPlannerDataAccessObject);
        CalorieTrackerController calorieTrackerController = createCalorieTrackerUseCase(
                viewManagerModel,
                myPlannerViewModel);
        return new MyPlannerView(viewManagerModel, myPlannerViewModel, myPlannerController, calorieTrackerController);
    }

    private static MyPlannerController createMyPlannerUseCase(
            ViewManagerModel viewManagerModel,
            MyPlannerViewModel myPlannerViewModel,
            MyPlannerDataAccessInterface myPlannerDataAccessObject) {
        MyPlannerOutputBoundary myPlannerPresenter = new MyPlannerPresenter(viewManagerModel, myPlannerViewModel);
        MyPlannerInputBoundary myPlannerInteractor = new MyPlannerInteractor(myPlannerDataAccessObject, myPlannerPresenter);
        return new MyPlannerController(myPlannerInteractor);
    }

    private static CalorieTrackerController createCalorieTrackerUseCase(
            ViewManagerModel viewManagerModel,
            MyPlannerViewModel myPlannerViewModel) {
        CalorieTrackerOutputBoundary calorieTrackerPresenter = new CalorieTrackerPresenter(viewManagerModel, myPlannerViewModel);
        CalorieTrackerInputBoundary calorieTrackerInteractor = new CalorieTrackerInteractor(null, calorieTrackerPresenter);
        return new CalorieTrackerController(calorieTrackerInteractor);
    }
}
