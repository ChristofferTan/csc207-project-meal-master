package use_case.my_profile;

import data_access.FilePlannerDataAccessObject;
import data_access.FileRecipeDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyProfileInteractorTest {
    @Test
    void successTest() {
        MyProfileInputData inputData = new MyProfileInputData("faraaz");
        MyProfileDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        PlannerFactory plannerFactory = new PlannerFactory();

        RecipeFactory recipeFactory = new RecipeFactory();
        FileRecipeDataAccessObject recipeDAO;
        recipeDAO = new FileRecipeDataAccessObject(recipeFactory);
        FilePlannerDataAccessObject plannerDAO = new FilePlannerDataAccessObject(new PlannerFactory(), recipeDAO);
        PlannerFactory plannerfactory = new PlannerFactory();
        Planner planner = plannerfactory.create("Janis");
        plannerDAO.saveNewPlanner(planner);

        User user = factory.create("faraaz", "Passw", "Faraaz", 19, "Man", 170, 60, plannerFactory.create("faraaz"));
        ((InMemoryUserDataAccessObject) userRepository).save(user);

        MyProfileOutputBoundary successPresenter = new MyProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(MyProfileOutputData output) {
                assertEquals("faraaz", output.getUsername());
                assertEquals("Faraaz", output.getName());
                assertEquals(19, output.getAge());
                assertEquals("Man", output.getGender());
                //assertEquals(170, output.getHeight());
                //assertEquals(60, output.getWeight());
                assertEquals(user, output.getUser());
            }
        };

        MyProfileInputBoundary interactor = new MyProfileInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
