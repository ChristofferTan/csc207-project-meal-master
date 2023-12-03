package interface_adapters.my_profile;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MyProfileViewModel extends ViewModel {
    public static final String TITLE_LABEL = "My Profile";
    public static final String USERNAME_LABEL = "Username: ";
    public static final String NAME_LABEL = "Name: ";
    public static final String AGE_LABEL = "Age: ";
    public static final String GENDER_LABEL = "Gender: ";
    public static final String HEIGHT_LABEL = "Height: ";
    public static final String HEIGHT_UNIT = "cm";
    public static final String WEIGHT_LABEL = "Weight: ";
    public static String WEIGHT_UNIT = "kg";
    public static String CALORIES_LABEL = "Estimated calories needed (per day): ";
    public static String CALORIES_UNIT = "kcal";
    public static String EDIT_PROFILE_BUTTON = "Edit Profile";
    private MyProfileState state = new MyProfileState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public MyProfileViewModel() {super("my profile");}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MyProfileState getState() {
        return state;
    }

    public void setState(MyProfileState state) {
        this.state = state;
    }
}
