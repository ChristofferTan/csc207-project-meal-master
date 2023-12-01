package use_case.signup;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.PlannerFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class SignupInteractorTest {
    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("Faraaz", "Ahmed", "Ahmed", "Faraaz", 19, "Man", 168, 60);
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Faraaz", user.getUsername());
                User user1 = userRepository.get("Faraaz");
                assertEquals("Faraaz", user1.getName());
                assertEquals(19, user1.getAge());
                assertEquals("Man", user1.getGender());
                assertEquals(168, user1.getHeight());
                assertEquals(60, user1.getWeight());
                assertTrue(userRepository.existsByName("Faraaz"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory(), new PlannerFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        SignupInputData inputData = new SignupInputData("Faraaz", "Ahmed", "wrong", "Faraaz", 19, "Man", 168, 60);
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory(), new PlannerFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        UserFactory factory = new CommonUserFactory();
        PlannerFactory plannerFactory = new PlannerFactory();
        User user1 = factory.create("Faraaz", "ganteng", "christoffer", 18, "Man", 170, 68, plannerFactory.create("Faraaz"));
        SignupInputData inputData = new SignupInputData("Faraaz", "Ahmed", "Ahmed", "Faraaz", 19, "Man", 168, 60);

        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user1);

        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory(), new PlannerFactory());
        interactor.execute(inputData);
    }
}