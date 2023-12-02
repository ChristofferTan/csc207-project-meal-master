package interface_adapters.add_friend;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddFriendViewModel extends ViewModel {
    private AddFriendState state = new AddFriendState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AddFriendViewModel() {
        super("add friend");
    }
    public void setState(AddFriendState state) {this.state = state;}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public AddFriendState getState() {return state;}
}
