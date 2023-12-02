package interface_adapters.edit_profile;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EditProfileViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Edit Profile View";
    public static final String NAME_LABEL = "Name";
    public static final String AGE_LABEL = "Age";
    public static final String GENDER_LABEL = "Gender";
    public static final String HEIGHT_LABEL = "Height";
    public static final String WEIGHT_LABEL = "Weight";

    public static final String EDIT_PROFILE_BUTTON_LABEL = "Save";

    private EditProfileState state = new EditProfileState();

    public EditProfileViewModel() {
        super("edit profile");
    }

    public void setState(EditProfileState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public EditProfileState getState() {
        return state;
    }
}