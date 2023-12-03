package interface_adapters.edit_profile;

import app.EditProfileFactory;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.Planner;
import entity.User;
import interface_adapters.ViewManagerModel;
import interface_adapters.my_profile.MyProfileViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EditProfileTest {
    private ViewManagerModel viewManagerModel;
    private EditProfileViewModel editProfileViewModel;
    private MyProfileViewModel myProfileViewModel;
    private FileUserDataAccessObject userDAO;
    private EditProfileController controller;
    private String username = "RazanAr3";

    @BeforeEach
    void init() {
        viewManagerModel = new ViewManagerModel();
        editProfileViewModel = new EditProfileViewModel();
        myProfileViewModel = new MyProfileViewModel();

        try {
            userDAO = new FileUserDataAccessObject(new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User newUser = new CommonUserFactory().create(username, "admin", "Rania", 19, "Woman", 155, 50, new Planner(username));
        userDAO.save(newUser);

        controller = EditProfileFactory.createEditProfileUseCase(viewManagerModel, editProfileViewModel, userDAO, myProfileViewModel);
    }
    @Test
    public void testEditProfile() {
        controller.execute(username, "Razan", 17, "Man", 177, 50);

        // Check that the fetched data is correct
        User user = userDAO.get(username);

        Assertions.assertEquals(user.getUsername(), username);
        Assertions.assertEquals(user.getName(), "Razan");
        Assertions.assertEquals(user.getAge(), 17);
        Assertions.assertEquals(user.getGender(), "Man");
        Assertions.assertEquals(user.getHeight(), 177);
        Assertions.assertEquals(user.getWeight(), 50);
    }
}
