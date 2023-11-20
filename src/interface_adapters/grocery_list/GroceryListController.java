package interface_adapters.grocery_list;

import use_case.grocery_list.GroceryListInputData;
import use_case.grocery_list.GroceryListInputBoundary;

public class GroceryListController {
    final GroceryListInputBoundary groceryListInteractor;

    public GroceryListController(GroceryListInputBoundary groceryListInteractor) {
        this.groceryListInteractor = groceryListInteractor;
    }

    public void execute(String username) {
        GroceryListInputData groceryListInputData = new GroceryListInputData(username);
        groceryListInteractor.execute(groceryListInputData);
    }
}
