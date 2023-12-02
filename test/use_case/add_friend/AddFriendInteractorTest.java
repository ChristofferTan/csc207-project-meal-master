package use_case.add_friend;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.PlannerFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class AddFriendInteractorTest{
    @Test
    void successTest() {
        AddFriendInputData inputData = new AddFriendInputData("Christoffer", "Janis");
        AddFriendUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        PlannerFactory plannerFactory = new PlannerFactory();
        User user1 = factory.create("Christoffer", "ganteng", "christoffer", 18, "Man", 170, 68, plannerFactory.create("Christoffer"));
        User user2 = factory.create("Janis", "jelek", "janis", 19, "Woman", 175, 90, plannerFactory.create("Janis"));
        ((InMemoryUserDataAccessObject) userRepository).save(user1);
        ((InMemoryUserDataAccessObject) userRepository).save(user2);
        AddFriendOutputBoundary successPresenter = new AddFriendOutputBoundary() {

            @Override
            public void prepareSuccesView(AddFriendOutputData addFriendOutputData) {
                assertTrue(userRepository.isFriend("Christoffer", "Janis"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        AddFriendInputBoundary interactor = new AddFriendInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
    @Test
    void failureFriendUsernameDoesNotExistTest() {
        AddFriendInputData inputData = new AddFriendInputData("Christoffer", "Janis");
        AddFriendUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        PlannerFactory plannerFactory = new PlannerFactory();
        User user1 = factory.create("Christoffer", "ganteng", "christoffer", 18, "Man", 170, 68, plannerFactory.create("Christoffer"));
        ((InMemoryUserDataAccessObject) userRepository).save(user1);
        ((InMemoryUserDataAccessObject) userRepository).save(user1);
        AddFriendOutputBoundary failurePresenter = new AddFriendOutputBoundary() {

            @Override
            public void prepareSuccesView(AddFriendOutputData addFriendOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("user does not exist.", error);
            }
        };

        AddFriendInputBoundary interactor = new AddFriendInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
    @Test
    void failureFriendHasBeenAddedTest() {
        AddFriendInputData inputData = new AddFriendInputData("Christoffer", "Janis");
        AddFriendUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        PlannerFactory plannerFactory = new PlannerFactory();
        User user1 = factory.create("Christoffer", "ganteng", "christoffer", 18, "Man", 170, 68, plannerFactory.create("Christoffer"));
        User user2 = factory.create("Janis", "jelek", "janis", 19, "Woman", 175, 90, plannerFactory.create("Janis"));
        ((InMemoryUserDataAccessObject) userRepository).save(user1);
        ((InMemoryUserDataAccessObject) userRepository).save(user2);
        userRepository.addFriend("Christoffer", "Janis");

        AddFriendOutputBoundary failurePresenter = new AddFriendOutputBoundary() {

            @Override
            public void prepareSuccesView(AddFriendOutputData addFriendOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("you have added Janis as friend.", error);
            }
        };

        AddFriendInputBoundary interactor = new AddFriendInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}