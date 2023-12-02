package use_case.edit_profile;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.PlannerFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class EditProfileInteractorTest {
    @Test
    void successTest() {
        EditProfileInputData inputData = new EditProfileInputData("RazanAr", "Razan", 17, "Male", 180, 77);
        EditProfileDataAccessInterface userRepository;
        UserFactory factory = new CommonUserFactory();
        PlannerFactory plannerFactory = new PlannerFactory();
        try {
            userRepository = new FileUserDataAccessObject(factory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User user = factory.create("RazanAr", "admin", "Nazar", 71, "Female", 190, 49, plannerFactory.create("RazanAr"));
        userRepository.save(user);
        EditProfileOutputBoundary successPresenter = new EditProfileOutputBoundary() {

            @Override
            public void prepareSuccessView(EditProfileOutputData editProfileOutputData) {
                assertEquals(userRepository.get("RazanAr").getName(), "Razan");
                assertEquals(userRepository.get("RazanAr").getAge(), 17);
                assertEquals(userRepository.get("RazanAr").getGender(), "Male");
                assertEquals(userRepository.get("RazanAr").getWeight(), 77);
                assertEquals(userRepository.get("RazanAr").getHeight(), 180);
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