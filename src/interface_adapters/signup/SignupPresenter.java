package interface_adapters.signup;

import interface_adapters.ViewManagerModel;
<<<<<<< HEAD
=======
import interface_adapters.login.LoginState;
>>>>>>> 400a6f05739da68f5805878f83f6b93d9f43f9d9
import interface_adapters.login.LoginViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public SignupPresenter(SignupViewModel signupViewModel, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
<<<<<<< HEAD
    public void prepareSuccessView(SignupOutputData user) {
        System.out.println(user.getUsername() + "has already been created");
=======
    public void prepareSuccessView(SignupOutputData response) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
>>>>>>> 400a6f05739da68f5805878f83f6b93d9f43f9d9
    }

    @Override
    public void prepareFailView(String error) {
<<<<<<< HEAD
        System.out.println(error);
=======
        SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
>>>>>>> 400a6f05739da68f5805878f83f6b93d9f43f9d9
    }
}
