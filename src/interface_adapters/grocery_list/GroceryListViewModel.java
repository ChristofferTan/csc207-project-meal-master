package interface_adapters.grocery_list;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GroceryListViewModel extends ViewModel {

        public static final String BACK_BUTTON_LABEL = "Back";

        private GroceryListState state = new GroceryListState();

        public GroceryListViewModel() {
            super("grocery list");
        }

        public void setState(GroceryListState state) {
            this.state = state;
        }

        private final PropertyChangeSupport support = new PropertyChangeSupport(this);

        @Override
        public void firePropertyChanged() {
            support.firePropertyChange("state", null, this.state);
        }

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            support.addPropertyChangeListener(listener);
        }

        public GroceryListState getState() {
            return state;
        }
}
