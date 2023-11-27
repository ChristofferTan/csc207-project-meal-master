package interface_adapters.my_planner;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MyPlannerViewModel extends ViewModel {
    public final String TITLE_LABEL = "My Planner View";
    public final String MY_PLANNER_LABEL = "My Planner";
    public final String CALORIE_TRACKER_LABEL = "Calorie Tracker";

    public static final String BACK_BUTTON_LABEL = "Back";

    private MyPlannerState state = new MyPlannerState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public MyPlannerViewModel() {
        super("my planner");
    }

    public MyPlannerState getState() {
        return state;
    }

    public void setState(MyPlannerState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
