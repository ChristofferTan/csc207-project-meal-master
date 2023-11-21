package use_case.after_generated_recipe;

public class AfterGeneratedRecipeInteractor implements AfterGeneratedRecipeInputBoundary{

    final AfterGeneratedRecipeDataAccessInterface afterGeneratedRecipeDataAccessInterface;
    final AfterGeneratedRecipeOutputBoundary afterGeneratedRecipePresenter;

    public AfterGeneratedRecipeInteractor(AfterGeneratedRecipeDataAccessInterface afterGeneratedRecipeDataAccessInterface, AfterGeneratedRecipeOutputBoundary afterGeneratedRecipeOutputBoundary) {
        this.afterGeneratedRecipeDataAccessInterface = afterGeneratedRecipeDataAccessInterface;
        this.afterGeneratedRecipePresenter = afterGeneratedRecipeOutputBoundary;
    }

    @Override
    public void execute(AfterGeneratedRecipeInputData afterGeneratedRecipeInputData) {
        AfterGeneratedRecipeOutputData afterGeneratedRecipeOutputData = new AfterGeneratedRecipeOutputData(afterGeneratedRecipeInputData.getUsername(), afterGeneratedRecipeInputData.getRecipe());
        afterGeneratedRecipePresenter.prepareSuccessView(afterGeneratedRecipeOutputData);
    }
}
