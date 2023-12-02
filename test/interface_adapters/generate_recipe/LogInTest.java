package interface_adapters.generate_recipe;

import app.LoginUseCaseFactory;
import data_access.FileUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.Planner;
import entity.User;
import interface_adapters.ViewManagerModel;
import interface_adapters.logged_in.LoggedInState;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.login.LoginController;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.LoginUserDataAccessInterface;

import javax.swing.text.View;
import java.io.IOException;

public class LogInTest {
    private ViewManagerModel viewManagerModel;
    private LoggedInViewModel loggedInViewModel;
    private LoginViewModel loginViewModel;
    private LoginUserDataAccessInterface userDAO;
    private LoginController controller;

    @BeforeEach
    void init() {
        viewManagerModel = new ViewManagerModel();
        viewManagerModel = new ViewManagerModel();
        loggedInViewModel = new LoggedInViewModel();
        loginViewModel = new LoginViewModel();
        try {
            userDAO = new FileUserDataAccessObject(new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String username = "andi";
        String password = "haihai";
        String repeatPassword = "haihai";
        String name = "Christoffer Tan";
        int age = 19;
        String gender = "Man";
        int height = 170;
        int weight = 67;

        User newUser = new CommonUserFactory().create(username, password, name, age, gender, height, weight, new Planner("andi"));
        userDAO.save(newUser);

        try {
            controller = LoginUseCaseFactory.createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDAO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testLogIn() {
        String username = "andi";
        String password = "haihai";
        
        controller.execute(username, password);

        // check that the username log in is correct
        LoggedInState loggedInState = loggedInViewModel.getState();
        User user = userDAO.get("andi");

        Assertions.assertEquals(user.getUsername(), loggedInState.getUsername());
        Assertions.assertEquals(viewManagerModel.getActiveView(), loggedInViewModel.getViewName());
    }

}
