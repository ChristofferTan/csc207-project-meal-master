package use_case.grocery_list;

import entity.Planner;

public class GroceryListInteractor implements GroceryListInputBoundary{
    final GroceryListDataAccessInterface dataAccessInterface;
    final GroceryListOutputBoundary groceryListPresenter;

    public GroceryListInteractor(GroceryListDataAccessInterface dataAccessInterface, GroceryListOutputBoundary groceryListOutputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.groceryListPresenter = groceryListOutputBoundary;
    }

    public void execute(GroceryListInputData groceryListInputData) {
        String username = groceryListInputData.getUsername();
        Planner planner = dataAccessInterface.getPlanner(username);
        GroceryListOutputData groceryListOutputData = new GroceryListOutputData(planner);
        groceryListPresenter.prepareSuccessView(groceryListOutputData);
    }
}
