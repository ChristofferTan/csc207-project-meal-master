package interface_adapters.my_planner;

import entity.Planner;

public class MyPlannerState {
    private String username = "";
    private Planner planner = null;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPlanner(Planner planner) {
        this.planner = planner;
    }

    public void setNoPlannerError(String noPlannerError) {
        this.noPlannerError = noPlannerError;
    }
}
