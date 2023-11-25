package interface_adapters.show_planner;

import entity.Planner;

public class ShowPlannerState {
    private String username = "";
    private Planner planner = null;

    public ShowPlannerState(ShowPlannerState copy) {
        username = copy.username;
        planner = copy.planner;
    }

    public ShowPlannerState() {}

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
}
