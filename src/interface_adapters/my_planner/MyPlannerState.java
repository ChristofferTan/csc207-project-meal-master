package interface_adapters.my_planner;

import entity.Planner;

public class MyPlannerState {
    private String username = "";
    private Planner planner = null;
    private int weeklyCalories = 0;
    private int averageDailyCalories = 0;
    private String noPlannerError = null;

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

    public int getAverageDailyCalories() {
        return averageDailyCalories;
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

    public void setAverageDailyCalories(int averageDailyCalories) {
        this.averageDailyCalories = averageDailyCalories;
    }

    public void setNoPlannerError(String noPlannerError) {
        this.noPlannerError = noPlannerError;
    }

    public void setNoCalorieTrackerError(String noCalorieTrackerError) {
        this.noPlannerError = noCalorieTrackerError;
    }
}
