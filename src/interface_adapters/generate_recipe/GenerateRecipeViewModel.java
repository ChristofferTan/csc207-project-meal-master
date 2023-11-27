package interface_adapters.generate_recipe;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GenerateRecipeViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Generate Recipe View";
    public static final String KEYWORD_LABEL = "Keyword";
    public static final String DIET_LABEL = "Diet";
    public static final String HEALTH_LABEL = "Health";
    public static final String CUISINE_TYPE_LABEL = "Cuisine Type";
    public static final String MEAL_TYPE_LABEL = "Meal Type";
    public static final String MIN_CALORIES_LABEL = "Min Calories";
    public static final String MAX_CALORIES_LABEL = "Max Calories";
    public static final String MAX_PREPARATION_TIME_LABEL = "Max Preparation Time";

    public static final String GENERATE_RECIPE_BUTTON_LABEL = "Generate Recipe";

    private GenerateRecipeState state = new GenerateRecipeState();

    public GenerateRecipeViewModel() {
        super("generate recipe");
    }

    public void setState(GenerateRecipeState state) {
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

    public GenerateRecipeState getState() {
        return state;
    }
}
