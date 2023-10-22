package entity;

import java.util.HashMap;
import java.util.List;

public class Planner {
    private final HashMap<String, List<Recipe>> planner;

    public Planner(HashMap<String, List<Recipe>> planner) {
        this.planner = planner;
    }

    public HashMap<String, List<Recipe>> getPlanner() {
        return planner;
    }
}
