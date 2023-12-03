package interface_adapters.generate_recipe;

import entity.MealType;
import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class GenerateRecipeViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Generate Recipe View";
    public static final String KEYWORD_LABEL = "Keyword";
    public static final String DIET_LABEL = "Diet";
    public static final String[] DIET_OPTIONS = new String[]{"balanced", "high-fiber", "high-protein", "low-carb", "low-fat", "low-sodium"};
    public static final String HEALTH_LABEL = "Health";
    public static final String[] HEALTH_OPTIONS = {"alcohol-free", "dairy-free", "egg-free", "fish-free", "gluten-free", "low-sugar", "peanut-free", "pork-free", "vegan"};
    public static final String CUISINE_TYPE_LABEL = "Cuisine Type";
    public static final String[] CUISINE_TYPE_OPTIONS = {"American", "British", "Caribbean", "Chinese", "French", "Indian", "Italian", "Japanese", "Mediterranean"};
    public static final String MEAL_TYPE_LABEL = "Meal Type";
    public static final String[] MEAL_TYPE_OPTIONS = {MealType.BREAKFAST.toString(), MealType.LUNCH.toString(), MealType.DINNER.toString()};
    public static final String MIN_CALORIES_LABEL = "Min Calories (in kcal)";
    public static final String MAX_CALORIES_LABEL = "Max Calories (in kcal)";
    public static final String MAX_PREPARATION_TIME_LABEL = "Max Preparation Time (in minutes)";

    public static final String GENERATE_RECIPE_BUTTON_LABEL = "Generate Recipe";
    public static final String BACK_BUTTON_LABEL = "Back";

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
