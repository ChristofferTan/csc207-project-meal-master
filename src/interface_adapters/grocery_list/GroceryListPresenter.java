package interface_adapters.grocery_list;

import interface_adapters.ViewManagerModel;
import use_case.grocery_list.GroceryListOutputBoundary;
import use_case.grocery_list.GroceryListOutputData;

public class GroceryListPresenter implements GroceryListOutputBoundary {
    private final GroceryListViewModel groceryListViewModel;
    private ViewManagerModel viewManagerModel;

    public GroceryListPresenter(GroceryListViewModel groceryListViewModel, ViewManagerModel viewManagerModel) {
        this.groceryListViewModel = groceryListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GroceryListOutputData groceryListOutputData) {

        GroceryListState groceryListState = groceryListViewModel.getState();
        groceryListState.setGroceryList(groceryListOutputData.getGroceryList());
        this.groceryListViewModel.setState(groceryListState);
        this.groceryListViewModel.firePropertyChanged();
    }
}
