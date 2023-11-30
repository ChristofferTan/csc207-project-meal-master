package interface_adapters.my_favorite_recipe;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MyFavoriteRecipeViewModel extends ViewModel {
    public static final String TITLE_LABEL = "My Favorite Recipe View";
    public static final String DELETE_LABEL = "Delete";
    public static final String BACK_LABEL = "Back";

    private MyFavoriteRecipeState state = new MyFavoriteRecipeState();

    public MyFavoriteRecipeViewModel() {
        super("my favorite recipe");
    }

    public void setState(MyFavoriteRecipeState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MyFavoriteRecipeState getState() {
        return state;
    }
}
