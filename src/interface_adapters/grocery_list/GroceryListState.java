package interface_adapters.grocery_list;

import java.util.ArrayList;

public class GroceryListState {
    private ArrayList<String> groceryList = new ArrayList<>();

    public GroceryListState(GroceryListState copy) {
        groceryList = copy.groceryList;
    }

    public GroceryListState() {
    }

    public String[] getGroceryList() {
        String[] grocery = new String[groceryList.size()];

        return groceryList.toArray(grocery);
    }

    public void setGroceryList(ArrayList<String> groceryList) {
        this.groceryList = groceryList;
    }
}
