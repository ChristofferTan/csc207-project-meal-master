package interface_adapters.my_planner;

import app.MyPlannerUseCaseFactory;
import data_access.FilePlannerDataAccessObject;
import data_access.FileRecipeDataAccessObject;
import entity.PlannerFactory;
import entity.RecipeFactory;
import interface_adapters.ViewManagerModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.my_planner.MyPlannerDataAccessInterface;
import view.MyPlannerView;

public class MyPlannerTest {
    private ViewManagerModel viewManagerModel;
    private MyPlannerViewModel myPlannerViewModel;
    private MyPlannerDataAccessInterface plannerDAO;
    private MyPlannerController controller;

    @BeforeEach
    void init() {
        viewManagerModel = new ViewManagerModel();
        myPlannerViewModel = new MyPlannerViewModel();
        plannerDAO = new FilePlannerDataAccessObject(new PlannerFactory(),
                new FileRecipeDataAccessObject(new RecipeFactory()));
        controller = MyPlannerUseCaseFactory.createMyPlannerUseCase(viewManagerModel, myPlannerViewModel, plannerDAO);
    }

    @Test
    public void testMyPlanner() {
        String username = "Christa";
        controller.execute(username);

        // check that the program correctly open my planner view
        Assertions.assertEquals(viewManagerModel.getActiveView(), myPlannerViewModel.getViewName());
    }
}
