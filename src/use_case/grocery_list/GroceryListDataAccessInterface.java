package use_case.grocery_list;

import entity.Planner;

public interface GroceryListDataAccessInterface {
    Planner getPlanner(String username);
}
