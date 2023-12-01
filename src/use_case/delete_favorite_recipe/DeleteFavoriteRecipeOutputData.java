package use_case.delete_favorite_recipe;

public class DeleteFavoriteRecipeOutputData {
    private final String username;
    private final String label;

    public DeleteFavoriteRecipeOutputData(String username, String label) {
        this.username = username;
        this.label = label;
    }

    public String getUsername() {
        return username;
    }

    public String getLabel() {
        return label;
    }
}



