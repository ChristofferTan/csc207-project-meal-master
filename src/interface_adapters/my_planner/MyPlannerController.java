package interface_adapters.my_planner;

import use_case.my_planner.MyPlannerInputBoundary;
import use_case.my_planner.MyPlannerInputData;

public class MyPlannerController {
    final MyPlannerInputBoundary myPlannerInteractor;

    public MyPlannerController(MyPlannerInputBoundary myPlannerInteractor) {
        this.myPlannerInteractor = myPlannerInteractor;
    }

    public void execute(String username) {
        MyPlannerInputData myPlannerInputData = new MyPlannerInputData(username);

        myPlannerInteractor.execute(myPlannerInputData);
    }
}
