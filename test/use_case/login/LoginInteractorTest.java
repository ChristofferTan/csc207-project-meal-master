package use_case.login;

import data_access.FilePlannerDataAccessObject;
import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoginInteractorTest {
    @Test
    void successTest() {
        UserFactory userFactory = new CommonUserFactory();
        LoginUserDataAccessInterface userDAO;
        PlannerFactory plannerFactory = new PlannerFactory();
        try {
            userDAO = new FileUserDataAccessObject(userFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        RecipeFactory recipeFactory = new RecipeFactory();
        FileRecipeDataAccessObject recipeDAO;

        recipeDAO = new FileRecipeDataAccessObject(recipeFactory);
        FilePlannerDataAccessObject plannerDAO = new FilePlannerDataAccessObject(new PlannerFactory(), recipeDAO);
        PlannerFactory plannerfactory = new PlannerFactory();
        Planner planner = plannerfactory.create("Budi");
        plannerDAO.saveNewPlanner(planner);

        User user1 = userFactory.create("Budi", "pekerti", "Budiman", 58, "Man", 91, 150, plannerFactory.create("Budi"));
        userDAO.save(user1);

        LoginInputData inputData = new LoginInputData("Budi", "pekerti");
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals(user.getUsername(), "Budi");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userDAO, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void FailureUsernameDoesNotExist()  {
        UserFactory userFactory = new CommonUserFactory();
        LoginUserDataAccessInterface userDAO;
        try {
            userDAO = new FileUserDataAccessObject(userFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LoginInputData inputData = new LoginInputData("Andi", "pekerti");
        LoginOutputBoundary failPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(error,  "Account does not exist: Andi");
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userDAO, failPresenter);
        interactor.execute(inputData);
    }
    @Test
    void FailurePasswordDoesNotMatch()  {
        UserFactory userFactory = new CommonUserFactory();
        LoginUserDataAccessInterface userDAO;
        try {
            userDAO = new FileUserDataAccessObject(userFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LoginInputData inputData = new LoginInputData("Budi", "tidakpekerti");
        LoginOutputBoundary failPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(error,  "Incorrect password for " + "Budi" + ".");
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userDAO, failPresenter);
        interactor.execute(inputData);
    }
}

