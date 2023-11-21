package use_case.my_planner;

import entity.Planner;

public class MyPlannerOutputData {
    private final Planner planner;
    private boolean useCaseFailed;

    public MyPlannerOutputData(Planner planner, boolean useCaseFailed) {
        this.planner = planner;
        this.useCaseFailed = useCaseFailed;
    }

    public Planner getPlanner() {
        return planner;
    }
}
