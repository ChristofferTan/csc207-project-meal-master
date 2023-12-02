package interface_adapters.my_favorite_recipe;

import entity.MealType;
import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.DayOfWeek;

public class MyFavoriteRecipeViewModel extends ViewModel {
    public static final String TITLE_LABEL = "My Favorite Recipe View";
    public static final String ADD_TO_PLANNER_LABEL = "Add to your planner for ";
    public static final String ADD_TO_PLANNER_DAY_LABEL = "on ";
    public static final String SUBMIT_LABEL = "Save to planner";
    public static final String SHOW_LABEL = "Open recipe URL";
    public static final String DELETE_LABEL = "Delete";
    public static final String BACK_LABEL = "Back";
    public static final String[] MEAL_TYPE_OPTIONS = {MealType.BREAKFAST.name(), MealType.LUNCH.name(), MealType.DINNER.name()};
    public static final String[] DAY_OPTIONS = {DayOfWeek.MONDAY.name(), DayOfWeek.TUESDAY.name(), DayOfWeek.WEDNESDAY.name(), DayOfWeek.THURSDAY.name(), DayOfWeek.FRIDAY.name(), DayOfWeek.SATURDAY.name(), DayOfWeek.SUNDAY.name()};

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
