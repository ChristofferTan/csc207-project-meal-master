package interface_adapters.my_planner;

import use_case.my_planner.MyPlannerOutputBoundary;
import interface_adapters.ViewManagerModel;
import use_case.my_planner.MyPlannerOutputData;

public class MyPlannerPresenter implements MyPlannerOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final MyPlannerViewModel myPlannerViewModel;

    public MyPlannerPresenter(ViewManagerModel viewManagerModel,
                              MyPlannerViewModel myPlannerViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.myPlannerViewModel = myPlannerViewModel;
    }

    @Override
    public void prepareSuccessView(MyPlannerOutputData response) {
        // On success,switch to the show planner view
        MyPlannerState myPlannerState = myPlannerViewModel.getState();
        myPlannerState.setPlanner(response.getPlanner());
        this.myPlannerViewModel.setState(myPlannerState);
        this.myPlannerViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(myPlannerViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
