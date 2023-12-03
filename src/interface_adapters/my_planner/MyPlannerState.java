package interface_adapters.my_planner;

import entity.Planner;

public class MyPlannerState {
    private String username = "";
    private Planner planner = null;
    private int weeklyCalories = 0;
    private int averageMealCalories = 0;
    private boolean noPlanner = false;
    private String noCalorieTrackerError = null;

    public MyPlannerState(MyPlannerState copy) {
        username = copy.username;
        planner = copy.planner;
    }

    public MyPlannerState() {}

    public String getUsername() {
        return username;
    }

    public Planner getPlanner() {
        return planner;
    }

    public int getWeeklyCalories() {
        return weeklyCalories;
    }

    public int getAverageMealCalories() {
        return averageMealCalories;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPlanner(Planner planner) {
        this.planner = planner;
    }

    public void setWeeklyCalories(int weeklyCalories) {
        this.weeklyCalories = weeklyCalories;
    }

    public void setAverageMealCalories(int averageMealCalories) {
        this.averageMealCalories = averageMealCalories;
    }

    public void setNoPlanner(boolean noPlanner) {
        this.noPlanner = noPlanner;
    }

    public void setNoCalorieTrackerError(String noCalorieTrackerError) {
        this.noCalorieTrackerError = noCalorieTrackerError;
    }
}
