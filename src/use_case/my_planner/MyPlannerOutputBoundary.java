package use_case.my_planner;

public interface MyPlannerOutputBoundary {
    void prepareSuccessView(MyPlannerOutputData myPlannerOutputData);
    void prepareFailView(String error);
}
