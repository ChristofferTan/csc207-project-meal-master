package use_case.save_recipe;

public class SaveRecipeInteractor implements SaveRecipeInputBoundary {
    final SaveRecipeDataAccessInterface dataAccessInterface;
    final SaveRecipeOutputBoundary saveRecipePresenter;

    public SaveRecipeInteractor(SaveRecipeDataAccessInterface dataAccessInterface,
                                    SaveRecipeOutputBoundary saveRecipeOutputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.saveRecipePresenter = saveRecipeOutputBoundary;
    }

    @Override
    public void execute(SaveRecipeInputData saveRecipeInputData) {
        SaveRecipeOutputData saveRecipeOutputData = new SaveRecipeOutputData();
        saveRecipePresenter.prepareSuccessView(saveRecipeOutputData);
    }
}

