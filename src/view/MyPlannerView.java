package view;

import app.MyPlannerUseCaseFactory;
import data_access.FilePlannerDataAccessObject;
import data_access.FileRecipeDataAccessObject;
import entity.*;
import interface_adapters.ViewManagerModel;
import interface_adapters.my_planner.MyPlannerController;
import interface_adapters.my_planner.MyPlannerState;
import interface_adapters.my_planner.MyPlannerViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URISyntaxException;
import java.time.DayOfWeek;

public class MyPlannerView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "my planner";

    private final ViewManagerModel viewManagerModel;
    private final MyPlannerViewModel myPlannerViewModel;
    private final MyPlannerController myPlannerController;


    JPanel plannerPanel;
    JPanel calorieTrackerPanel;
    final JButton back;


    /**
     * A window with a table showing the recipe and link of this user for each mealType and day of the week.
     * @param myPlannerViewModel
     */
    public MyPlannerView(ViewManagerModel viewManagerModel, MyPlannerViewModel myPlannerViewModel, MyPlannerController myPlannerController) {
        this.myPlannerViewModel = myPlannerViewModel;
        this.myPlannerViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.myPlannerController = myPlannerController;

        // Create a panel for the planner table
        plannerPanel = new JPanel(new GridLayout(4, 8));
        this.add(plannerPanel, BorderLayout.CENTER);

        // Create a panel for the calorie tracker
        calorieTrackerPanel = new JPanel(new GridLayout(4, 1));
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
            // TODO viewManagerModel.setActiveView(<back to previous view (should be the dashboard)>);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MyPlannerState state = (MyPlannerState) evt.getNewValue();
        Planner planner = state.getPlanner();
        int weeklyCalories = state.getWeeklyCalories();
        int averageDailyCalories = state.getAverageDailyCalories();

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
        for (DayOfWeek day : DayOfWeek.values()) {
            // Add label for the first column
            plannerPanel.add(new JLabel(day.name(), JLabel.CENTER));

            for (MealType mealType : MealType.values()) {
                Recipe recipe = planner.get(day, mealType);
                if (recipe == null) {
                    plannerPanel.add(new JLabel("No recipe", JLabel.CENTER));
                } else {
                    JButton button = new JButton(recipe.getLabel());
                    button.addActionListener(e -> openLink(recipe.getRecipeUrl()));
                    plannerPanel.add(button);
                }
            }
        }

        // Fill in the panel for the calorie tracker
        calorieTrackerPanel.add(new JLabel("", JLabel.LEFT));
        calorieTrackerPanel.add(new JLabel("Calorie Tracker", JLabel.LEFT));
        calorieTrackerPanel.add(new JLabel("Weekly calories: " + weeklyCalories, JLabel.LEFT));
        calorieTrackerPanel.add(new JLabel("Average daily calories: " + averageDailyCalories, JLabel.LEFT));
    }
}
