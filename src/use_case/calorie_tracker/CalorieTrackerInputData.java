package use_case.calorie_tracker;

import entity.Planner;

public class CalorieTrackerInputData {
    final private Planner planner;

    public CalorieTrackerInputData(Planner planner) {
        this.planner = planner;
    }

    public Planner getPlanner() {
        return planner;
    }
}
