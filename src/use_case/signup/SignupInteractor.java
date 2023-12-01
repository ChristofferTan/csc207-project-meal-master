package use_case.signup;

import entity.Planner;
import entity.PlannerFactory;
import entity.User;
import entity.UserFactory;

public class SignupInteractor implements SignupInputBoundary{
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;
    final PlannerFactory plannerFactory;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccessObject, SignupOutputBoundary userPresenter,
                            UserFactory userFactory, PlannerFactory plannerFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
        this.plannerFactory = plannerFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else {
            Planner planner = plannerFactory.create(signupInputData.getUsername());

            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), signupInputData.getName(), signupInputData.getAge(), signupInputData.getGender(), signupInputData.getHeight(), signupInputData.getWeight(), planner);
            userDataAccessObject.save(user);


            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
