package interface_adapters.delete_favorite_recipe;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteFavoriteRecipeViewModel extends ViewModel {
    private DeleteFavoriteRecipeState state = new DeleteFavoriteRecipeState();

    public DeleteFavoriteRecipeViewModel() {
        super("delete favorite recipe");
    }

    public void setState(DeleteFavoriteRecipeState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public DeleteFavoriteRecipeState getState() {
        return state;
    }
}
