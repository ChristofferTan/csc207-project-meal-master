package use_case.grocery_list;

public class GroceryListInputData {
    final private String username;

    public GroceryListInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
