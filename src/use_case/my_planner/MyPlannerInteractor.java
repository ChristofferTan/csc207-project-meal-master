package use_case.my_planner;

import entity.Planner;

public class MyPlannerInteractor implements MyPlannerInputBoundary {
    final MyPlannerDataAccessInterface myPlannerDataAccessObject;
    final MyPlannerOutputBoundary myPlannerPresenter;


    public MyPlannerInteractor(MyPlannerDataAccessInterface myPlannerDataAccessInterface,
                               MyPlannerOutputBoundary myPlannerOutputBoundary) {
        this.myPlannerDataAccessObject = myPlannerDataAccessInterface;
        this.myPlannerPresenter = myPlannerOutputBoundary;
    }

    @Override
    public void execute(MyPlannerInputData myPlannerInputData) {
        String username = myPlannerInputData.getUsername();
        if (!myPlannerDataAccessObject.isPlannerExistsByUsername(username)) {
            myPlannerPresenter.prepareFailView(username + "'s planner does not exist.");
        } else {
            Planner planner = myPlannerDataAccessObject.getPlanner(username);

            MyPlannerOutputData myPlannerOutputData = new MyPlannerOutputData(planner, false);
            myPlannerPresenter.prepareSuccessView(myPlannerOutputData);
        }
    }
}
