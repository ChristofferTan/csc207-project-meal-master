package use_case.my_planner;

import entity.Planner;

public interface MyPlannerDataAccessInterface {
    Planner getPlanner(String username);
    boolean isPlannerExistsByUsername(String username);
}
