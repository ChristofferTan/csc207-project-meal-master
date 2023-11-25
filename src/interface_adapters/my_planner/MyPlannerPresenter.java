package interface_adapters.my_planner;

import interface_adapters.show_planner.ShowPlannerState;
import interface_adapters.show_planner.ShowPlannerViewModel;
import use_case.my_planner.MyPlannerOutputBoundary;
import interface_adapters.ViewManagerModel;
import use_case.my_planner.MyPlannerOutputData;

public class MyPlannerPresenter implements MyPlannerOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private final MyPlannerViewModel myPlannerViewModel;
    private final ShowPlannerViewModel showPlannerViewModel;

    public MyPlannerPresenter(ViewManagerModel viewManagerModel,
                              MyPlannerViewModel myPlannerViewModel,
                              ShowPlannerViewModel showPlannerViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.myPlannerViewModel = myPlannerViewModel;
        this.showPlannerViewModel = showPlannerViewModel;
    }

    @Override
    public void prepareSuccessView(MyPlannerOutputData response) {
        // On success,switch to the show planner view
        ShowPlannerState showPlannerState = showPlannerViewModel.getState();
        showPlannerState.setPlanner(response.getPlanner());
        this.showPlannerViewModel.setState(showPlannerState);
        this.showPlannerViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(showPlannerViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        MyPlannerState myPlannerState = myPlannerViewModel.getState();
        myPlannerState.setNoPlannerError(error);
        myPlannerViewModel.firePropertyChanged();
    }
}
