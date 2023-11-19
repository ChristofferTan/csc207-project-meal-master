package interface_adapters.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {
    final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }
    public void execute(String username, String password, String repeatPassword, String name, int age, String gender, int height, int weight, String activityLevel) {
        SignupInputData signupInputData = new SignupInputData(username, password, repeatPassword, name, age, gender, height, weight, activityLevel);
        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
