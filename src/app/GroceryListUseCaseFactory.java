package app;

import interface_adapters.ViewManagerModel;
import interface_adapters.grocery_list.GroceryListController;
import interface_adapters.grocery_list.GroceryListPresenter;
import interface_adapters.grocery_list.GroceryListViewModel;
import use_case.grocery_list.GroceryListDataAccessInterface;
import use_case.grocery_list.GroceryListInputBoundary;
import use_case.grocery_list.GroceryListInteractor;
import use_case.grocery_list.GroceryListOutputBoundary;
import view.GroceryListView;

public class GroceryListUseCaseFactory {
    private GroceryListUseCaseFactory() {}

    public static GroceryListView create(ViewManagerModel viewManagerModel, GroceryListViewModel groceryListViewModel, GroceryListDataAccessInterface groceryListDataAccessInterface) {
        GroceryListController groceryListController = createGroceryListUseCase(viewManagerModel, groceryListViewModel, groceryListDataAccessInterface);
        return new GroceryListView(groceryListController, groceryListViewModel, viewManagerModel);
    }
    public static GroceryListController createGroceryListUseCase(ViewManagerModel viewManagerModel, GroceryListViewModel groceryListViewModel, GroceryListDataAccessInterface dataAccessInterface) {
        GroceryListOutputBoundary groceryListOutputBoundary = new GroceryListPresenter(groceryListViewModel, viewManagerModel);
        GroceryListInputBoundary groceryListInteractor = new GroceryListInteractor(dataAccessInterface, groceryListOutputBoundary);
        return new GroceryListController(groceryListInteractor);
    }
}
