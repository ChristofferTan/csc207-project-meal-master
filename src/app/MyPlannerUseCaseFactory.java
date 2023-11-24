package app;

import interface_adapters.ViewManagerModel;
import interface_adapters.my_planner.MyPlannerController;
import interface_adapters.my_planner.MyPlannerPresenter;
import interface_adapters.my_planner.MyPlannerViewModel;
import interface_adapters.show_planner.ShowPlannerViewModel;
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
            ShowPlannerViewModel showPlannerViewModel,
            MyPlannerDataAccessInterface myPlannerDataAccessObject) {
        MyPlannerController myPlannerController = createMyPlannerUseCase(
                viewManagerModel,
                myPlannerViewModel,
                showPlannerViewModel,
                myPlannerDataAccessObject);
        return new MyPlannerView(myPlannerViewModel, myPlannerController);
    }

    private static MyPlannerController createMyPlannerUseCase(
            ViewManagerModel viewManagerModel,
            MyPlannerViewModel myPlannerViewModel,
            ShowPlannerViewModel showPlannerViewModel,
            MyPlannerDataAccessInterface myPlannerDataAccessObject) {
        MyPlannerOutputBoundary myPlannerPresenter = new MyPlannerPresenter(viewManagerModel, myPlannerViewModel, showPlannerViewModel);
        MyPlannerInputBoundary myPlannerInteractor = new MyPlannerInteractor(myPlannerDataAccessObject, myPlannerPresenter);
        return new MyPlannerController(myPlannerInteractor);
    }
}
