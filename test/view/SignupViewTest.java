package view;

import app.SignupUseCaseFactory;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupViewModel;

import java.io.IOException;

public class SignupViewTest {
    @org.junit.Test
    public void testSignup() {
        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject(new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SignupView signupView = SignupUseCaseFactory.create(new ViewManagerModel(), new LoginViewModel(), new SignupViewModel(), userDataAccessObject);
    }
}
