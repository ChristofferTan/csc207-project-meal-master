package interface_adapters.my_profile;

import app.MyProfileFactory;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.Planner;
import entity.User;
import interface_adapters.ViewManagerModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MyProfileTest {
    private ViewManagerModel viewManagerModel;
    private MyProfileViewModel myProfileViewModel;
    private FileUserDataAccessObject userDAO;
    private MyProfileController controller;
    private String username = "RazanAr2";

    @BeforeEach
    void init() {
        viewManagerModel = new ViewManagerModel();
        myProfileViewModel = new MyProfileViewModel();

        try {
            userDAO = new FileUserDataAccessObject(new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User newUser = new CommonUserFactory().create(username, "admin", "Razan", 17, "Man", 177, 70, new Planner(username));
        userDAO.save(newUser);

        controller = MyProfileFactory.createMyProfileUseCase(viewManagerModel, myProfileViewModel, userDAO);
    }
    @Test
    public void testMyProfile() {
        String username = "RazanAr2";

        controller.execute(username);

        // Check that the fetched data is correct
        MyProfileState state = myProfileViewModel.getState();
        User user = userDAO.get(username);

        Assertions.assertEquals(user.getUsername(), state.getUsername());
        Assertions.assertEquals(user.getAge(), state.getAge());
        Assertions.assertEquals(user.getGender(), state.getGender());
        Assertions.assertEquals(user.getHeight(), state.getHeight());
        Assertions.assertEquals(user.getWeight(), state.getWeight());
    }
}
