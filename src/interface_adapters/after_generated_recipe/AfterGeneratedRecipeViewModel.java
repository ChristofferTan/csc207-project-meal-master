package interface_adapters.after_generated_recipe;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AfterGeneratedRecipeViewModel extends ViewModel {
    public static final String SUBMIT_BUTTON_LABEL = "Submit";
    public static final String SERVINGS_LABEL = "Number of servings: ";
    public static final String CALORIES_INFO_LABEL = "Calories: ";
    public static final String PREPARATION_LABEL = "Preparation time: ";
    public static final String ADD_TO_PLANNER_LABEL = "Add to your planner for ";
    public static final String ADD_TO_PLANNER_DAY_LABEL = "on ";
    public static final String ADD_TO_FAVORITE_LIST_BUTTON_LABEL = "Favorite";
    public static final String BACK_BUTTON_LABEL = "Back";

    private AfterGeneratedRecipeState state = new AfterGeneratedRecipeState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AfterGeneratedRecipeViewModel() {super("after generated recipe");}
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public AfterGeneratedRecipeState getState() {return state;}
    public void setState(AfterGeneratedRecipeState state) {this.state = state;}
}
