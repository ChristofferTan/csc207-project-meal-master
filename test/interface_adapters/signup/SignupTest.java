package interface_adapters.signup;

import app.SignupUseCaseFactory;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.Planner;
import entity.User;
import interface_adapters.ViewManagerModel;
import interface_adapters.login.LoginState;
import interface_adapters.login.LoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupUserDataAccessInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SignupTest {
    private ViewManagerModel viewManagerModel;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private SignupUserDataAccessInterface userDAO;
    private SignupController controller;

    @BeforeEach
    void init() {
        viewManagerModel = new ViewManagerModel();
        signupViewModel = new SignupViewModel();
        loginViewModel = new LoginViewModel();
        userDAO = new InMemoryUserDataAccessObject();
        controller = SignupUseCaseFactory.createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDAO);
    }
    @Test
    public void testSignup() {
        String username = "christoffer";
        String password = "haihai";
        String repeatPassword = "haihai";
        String name = "Christoffer Tan";
        int age = 19;
        String gender = "Man";
        int height = 170;
        int weight = 67;

        // execute the use case
        controller.execute(username, password, repeatPassword, name, age, gender, height, weight);

        // check that the user saved is correct
        LoginState state = loginViewModel.getState();
        User user = userDAO.get("christoffer");

        assertEquals(state.getUsername(), user.getUsername());

        // check that the view is changed to login view
        assertEquals(viewManagerModel.getActiveView(), loginViewModel.getViewName());
    }
    @Test
    public void testSignupDataAccess() {
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

        // check that user saved is correct
        User actualSavedUser = userDAO.get("andi");

        assertNotNull(actualSavedUser);
        assertEquals(newUser.getUsername(), actualSavedUser.getUsername());
        assertEquals(newUser.getPassword(), actualSavedUser.getPassword());
        assertEquals(newUser.getName(), actualSavedUser.getName());
        assertEquals(newUser.getAge(), actualSavedUser.getAge());
        assertEquals(newUser.getGender(), actualSavedUser.getGender());
        assertEquals(newUser.getHeight(), actualSavedUser.getHeight());
        assertEquals(newUser.getWeight(), actualSavedUser.getWeight());
    }
}
