package interface_adapters.signup;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {

    public static final String CLEAR_BUTTON_LABEL = "Clear";
    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public static final String NAME_LABEL = "Enter your name";
    public static final String AGE_LABEL = "Enter your age";
    public static final String GENDER_LABEL = "Enter your gender";
    public static final String HEIGHT_LABEL = "Enter your height in cm";
    public static final String WEIGHT_LABEL = "Enter your weight in kg";
    public static final String ACTIVITY_LEVEL = "Enter your activity level";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String LOGIN_BUTTON_LABEL = "Log in";

    private SignupState state = new SignupState();

    public SignupViewModel() {
        super("sign up");
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignupState getState() {
        return state;
    }
}

