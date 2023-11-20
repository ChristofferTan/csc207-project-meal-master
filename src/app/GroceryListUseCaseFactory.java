package app;

import interface_adapters.grocery_list.GroceryListController;
import interface_adapters.grocery_list.GroceryListPresenter;
import use_case.grocery_list.GroceryListDataAccessInterface;
import use_case.grocery_list.GroceryListInputBoundary;
import use_case.grocery_list.GroceryListInteractor;
import use_case.grocery_list.GroceryListOutputBoundary;

public class GroceryListUseCaseFactory {
    private GroceryListUseCaseFactory() {}

    public static GroceryListController createGroceryListUseCase(GroceryListDataAccessInterface dataAccessInterface) {
        GroceryListOutputBoundary groceryListOutputBoundary = new GroceryListPresenter();
        GroceryListInputBoundary groceryListInteractor = new GroceryListInteractor(dataAccessInterface, groceryListOutputBoundary);
        return new GroceryListController(groceryListInteractor);
    }
}
