package view;

import entity.*;
import interface_adapters.ViewManagerModel;
import interface_adapters.calorie_tracker.CalorieTrackerController;
import interface_adapters.my_planner.MyPlannerController;
import interface_adapters.my_planner.MyPlannerState;
import interface_adapters.my_planner.MyPlannerViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URISyntaxException;
import java.time.DayOfWeek;
import java.util.HashMap;

public class MyPlannerView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "my planner";

    private final ViewManagerModel viewManagerModel;
    private final MyPlannerViewModel myPlannerViewModel;
    private final MyPlannerController myPlannerController;
    private final CalorieTrackerController calorieTrackerController;


    JPanel plannerPanel;
    JPanel calorieTrackerPanel;
    HashMap<MealType, HashMap<DayOfWeek, JButton>> recipesPlaceholder = new HashMap<>();
    JLabel[] calorieTrackersPlaceholder = new JLabel[2];
    final JButton back;


    /**
     * A window with a table showing the recipe and link of this user for each mealType and day of the week.
     * @param myPlannerViewModel
     */
    public MyPlannerView(ViewManagerModel viewManagerModel, MyPlannerViewModel myPlannerViewModel, MyPlannerController myPlannerController, CalorieTrackerController calorieTrackerController) {
        this.myPlannerViewModel = myPlannerViewModel;
        this.myPlannerViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.myPlannerController = myPlannerController;
        this.calorieTrackerController = calorieTrackerController;

        setLayout(new GridLayout(3, 1));

        // Create a panel for the planner table
        plannerPanel = new JPanel(new GridLayout(4, 8));
        // Add an empty label for the top-left cell
        plannerPanel.add(new JLabel());
        // Add labels for the first row
        plannerPanel.add(new JLabel(DayOfWeek.MONDAY.name(), JLabel.CENTER));
        plannerPanel.add(new JLabel(DayOfWeek.TUESDAY.name(), JLabel.CENTER));
        plannerPanel.add(new JLabel(DayOfWeek.WEDNESDAY.name(), JLabel.CENTER));
        plannerPanel.add(new JLabel(DayOfWeek.THURSDAY.name(), JLabel.CENTER));
        plannerPanel.add(new JLabel(DayOfWeek.FRIDAY.name(), JLabel.CENTER));
        plannerPanel.add(new JLabel(DayOfWeek.SATURDAY.name(), JLabel.CENTER));
        plannerPanel.add(new JLabel(DayOfWeek.SUNDAY.name(), JLabel.CENTER));
        // Fill in the labels (for the first column) and buttons with hyperlinks for the rest of the cells

        for (MealType mealType : MealType.values()) {
            plannerPanel.add(new JLabel(mealType.name(), JLabel.CENTER));
            recipesPlaceholder.put(mealType, new HashMap<>());
            for (DayOfWeek day : DayOfWeek.values()) {
                JButton recipePlaceholder = new JButton("-");
                recipesPlaceholder.get(mealType).put(day, recipePlaceholder);
                plannerPanel.add(recipePlaceholder);
            }
        }
        this.add(plannerPanel, BorderLayout.CENTER);

        // Create a panel for the calorie tracker
        calorieTrackerPanel = new JPanel(new GridLayout(4, 1));
        JLabel weeklyCaloriesLabel = new JLabel("Weekly calories: 0", JLabel.LEFT);
        JLabel averageDailyCaloriesLabel = new JLabel("Average daily calories: 0", JLabel.LEFT);
        calorieTrackersPlaceholder[0] = weeklyCaloriesLabel;
        calorieTrackersPlaceholder[1] = averageDailyCaloriesLabel;
        calorieTrackerPanel.add(new JLabel("", JLabel.LEFT));
        calorieTrackerPanel.add(new JLabel("CALORIE TRACKER", JLabel.LEFT));
        calorieTrackerPanel.add(weeklyCaloriesLabel);
        calorieTrackerPanel.add(averageDailyCaloriesLabel);
        this.add(calorieTrackerPanel, BorderLayout.SOUTH);

        // Create a panel for the buttons
        JPanel buttons = new JPanel();
        back = new JButton(myPlannerViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);
        back.addActionListener(this);
        this.add(buttons);
    }

    private void openLink(String link) {
        try {
            Desktop.getDesktop().browse(new java.net.URI(link));
        } catch (URISyntaxException | java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public MyPlannerViewModel getMyPlannerViewModel() {
        return myPlannerViewModel;
    }

    public MyPlannerController getMyPlannerController() {
        return myPlannerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)) {
                viewManagerModel.setActiveView("logged in");
                viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        calorieTrackerController.execute(getMyPlannerViewModel().getState().getPlanner());
        MyPlannerState state = (MyPlannerState) evt.getNewValue();
        Planner planner = state.getPlanner();
        int weeklyCalories = state.getWeeklyCalories();
        int averageMealCalories = state.getAverageMealCalories();

        // Fill in the buttons with hyperlinks for available recipes
        for (MealType mealType : MealType.values()) {
            for (DayOfWeek day : DayOfWeek.values()) {
                if (planner != null) {
                    Recipe recipe = planner.get(day, mealType);
                    if (recipe != null) {
                        JButton button = recipesPlaceholder.get(mealType).get(day);
                        button.setText(recipe.getLabel());
                        button.addActionListener(e -> openLink(recipe.getRecipeUrl()));
                    }
                }
            }
        }
        // Fill in the panel for the calorie tracker
        calorieTrackersPlaceholder[0].setText("Weekly calories: " + weeklyCalories);
        calorieTrackersPlaceholder[1].setText("Average calories per meal: " + averageMealCalories);
    }
}
