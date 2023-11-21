package interface_adapters.show_planner;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShowPlannerViewModel extends ViewModel {
    public final String TITLE_LABEL = "Show Planner View";
    private ShowPlannerState state = new ShowPlannerState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public ShowPlannerViewModel() {
        super("show planner");
    }

    public ShowPlannerState getState() {
        return state;
    }

    public void setState(ShowPlannerState state) {
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
