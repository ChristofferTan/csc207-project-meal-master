package use_case.delete_favorite_recipe;

public class DeleteFavoriteRecipeInputData {
    final private String username;
    final private String label;

    public DeleteFavoriteRecipeInputData(String username, String label) {
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
