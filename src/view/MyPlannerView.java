package view;

import interface_adapters.my_planner.MyPlannerController;
import interface_adapters.my_planner.MyPlannerViewModel;

public class MyPlannerView {
    private final MyPlannerViewModel myPlannerViewModel;
    private final MyPlannerController myPlannerController;

    public MyPlannerView(MyPlannerViewModel myPlannerViewModel, MyPlannerController myPlannerController) {
        this.myPlannerViewModel = myPlannerViewModel;
        this.myPlannerController = myPlannerController;
    }

    public MyPlannerController getMyPlannerController() {
        return myPlannerController;
    }

    public MyPlannerViewModel getMyPlannerViewModel() {
        return myPlannerViewModel;
    }
}
