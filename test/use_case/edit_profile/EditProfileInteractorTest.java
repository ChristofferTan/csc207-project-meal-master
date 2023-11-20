package use_case.edit_profile;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class EditProfileInteractorTest {
    @Test
    void successTest() {
        EditProfileInputData inputData = new EditProfileInputData("RazanAr", "Razan", 17, "Male", 70, 177);
        EditProfileDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("RazanAr", "admin", "Nazar", 71, "Female", 50, 149);
        ((InMemoryUserDataAccessObject) userRepository).save(user);
        EditProfileOutputBoundary successPresenter = new EditProfileOutputBoundary() {

            @Override
            public void prepareSuccessView(EditProfileOutputData editProfileOutputData) {
                assertEquals(((InMemoryUserDataAccessObject) userRepository).get("RazanAr").getName(), "Razan");
                assertEquals(((InMemoryUserDataAccessObject) userRepository).get("RazanAr").getAge(), 17);
                assertEquals(((InMemoryUserDataAccessObject) userRepository).get("RazanAr").getGender(), "Male");
                assertEquals(((InMemoryUserDataAccessObject) userRepository).get("RazanAr").getWeight(), 70);
                assertEquals(((InMemoryUserDataAccessObject) userRepository).get("RazanAr").getHeight(), 177);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        EditProfileInputBoundary interactor = new EditProfileInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}