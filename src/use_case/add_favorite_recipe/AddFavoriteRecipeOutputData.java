package use_case.add_favorite_recipe;

public class AddFavoriteRecipeOutputData {
    private final String label;
    private final String username;

    public AddFavoriteRecipeOutputData(String label, String username) {
        this.label = label;
        this.username = username;
    }

    public String getLabel() {
        return label;
    }

    public String getUsername() {
        return username;
    }
}
