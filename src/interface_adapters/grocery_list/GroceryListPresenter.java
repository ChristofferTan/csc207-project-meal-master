package interface_adapters.grocery_list;

import use_case.grocery_list.GroceryListInputBoundary;
import use_case.grocery_list.GroceryListOutputBoundary;
import use_case.grocery_list.GroceryListOutputData;

public class GroceryListPresenter implements GroceryListOutputBoundary {

    public GroceryListPresenter() {
    }

    @Override
    public void prepareSuccessView(GroceryListOutputData groceryListOutputData) {
        System.out.println("Grocery list for " + groceryListOutputData.getUsername() + ":");
        for (String item : groceryListOutputData.getGroceryList()) {
            System.out.println(item);
        }
    }
}
