package use_case.delete_favorite_recipe;

public class DeleteFavoriteRecipeOutputData {
    private final String label;

    public DeleteFavoriteRecipeOutputData(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



