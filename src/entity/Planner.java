package entity;

import java.util.HashMap;
import java.util.ArrayList;

public class Planner {
    private HashMap<String, ArrayList<Recipe>> planner;

    public Planner(HashMap<String, ArrayList<Recipe>> planner) {
        this.planner = planner;
    }

    public HashMap<String, ArrayList<Recipe>> getPlanner() {
        return planner;
    }

    public void setPlanner(HashMap<String, ArrayList<Recipe>> planner) {
        this.planner = planner;
    }
}
