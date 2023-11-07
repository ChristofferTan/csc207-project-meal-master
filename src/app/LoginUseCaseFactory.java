package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.logged_in.LoggedInViewModel;
import view.LoggedInView;
import interface_adapters.login.LoginController;
import interface_adapters.login.LoginPresenter;
import interface_adapters.login.LoginViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory() {}

    public static LoginView create (
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file");
        }
        return null;
    }

    private static LoginController createLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoggedInViewModel loggedInViewModel, LoginUserDataAccessInterface userDataAccessObject) throws IOException {
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loginViewModel, loggedInViewModel, viewManagerModel);
        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
