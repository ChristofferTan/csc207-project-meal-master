package view;

import entity.MealType;
import interface_adapters.ViewManagerModel;
import interface_adapters.add_favorite_recipe.AddFavoriteRecipeController;
import interface_adapters.after_generated_recipe.AfterGeneratedRecipeState;
import interface_adapters.after_generated_recipe.AfterGeneratedRecipeViewModel;
import interface_adapters.generate_recipe.GenerateRecipeState;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import interface_adapters.save_recipe.SaveRecipeController;
import view.utilities.MultiSelectDropdownPanel;
import view.utilities.SingleSelectDropdownPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.DayOfWeek;

public class AfterGeneratedRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "after generated recipe";
    private final AfterGeneratedRecipeViewModel afterGeneratedRecipeViewModel;
    private final GenerateRecipeViewModel generateRecipeViewModel;
    private final SaveRecipeController saveRecipeController;
    private final AddFavoriteRecipeController addFavoriteRecipeController;
    private final ViewManagerModel viewManagerModel;

    JLabel recipeLabel, servings, calories, preparation;
    private final SingleSelectDropdownPanel mealTypeInputField;
    private final SingleSelectDropdownPanel dayInputField;
    final JButton submit, favorite, back;
    URI recipeURL;

    public AfterGeneratedRecipeView(AfterGeneratedRecipeViewModel afterGeneratedRecipeViewModel, GenerateRecipeViewModel generateRecipeViewModel, SaveRecipeController saveRecipeController, AddFavoriteRecipeController addFavoriteRecipeController, ViewManagerModel viewManagerModel) {
        this.afterGeneratedRecipeViewModel = afterGeneratedRecipeViewModel;
        this.generateRecipeViewModel = generateRecipeViewModel;
        this.saveRecipeController = saveRecipeController;
        this.addFavoriteRecipeController = addFavoriteRecipeController;
        this.viewManagerModel = viewManagerModel;
        afterGeneratedRecipeViewModel.addPropertyChangeListener(this);

        recipeLabel = new JLabel();
        recipeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipeLabel.setForeground(Color.BLUE);
        recipeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel servingsInfo = new JLabel(AfterGeneratedRecipeViewModel.SERVINGS_LABEL);
        servings = new JLabel();

        JLabel caloriesInfo = new JLabel(AfterGeneratedRecipeViewModel.CALORIES_INFO_LABEL);
        calories = new JLabel();

        JLabel preparationInfo = new JLabel(AfterGeneratedRecipeViewModel.PREPARATION_LABEL);
        preparation = new JLabel();

        JLabel addToPlannerInfo = new JLabel(AfterGeneratedRecipeViewModel.ADD_TO_PLANNER_LABEL);
        JLabel dayInPlannerInfo = new JLabel(AfterGeneratedRecipeViewModel.ADD_TO_PLANNER_DAY_LABEL);

        JPanel servingsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        servingsPanel.add(servingsInfo);
        servingsPanel.add(servings);

        JPanel caloriesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        caloriesPanel.add(caloriesInfo);
        caloriesPanel.add(calories);

        JPanel preparationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        preparationPanel.add(preparationInfo);
        preparationPanel.add(preparation);

        mealTypeInputField = new SingleSelectDropdownPanel(null, AfterGeneratedRecipeViewModel.MEAL_TYPE_OPTIONS);
        dayInputField = new SingleSelectDropdownPanel(null, AfterGeneratedRecipeViewModel.DAY_OPTIONS);
        JPanel addToPlannerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addToPlannerPanel.add(addToPlannerInfo);
        addToPlannerPanel.add(mealTypeInputField);
        addToPlannerPanel.add(dayInPlannerInfo);
        addToPlannerPanel.add(dayInputField);

        JPanel buttons = new JPanel();
        submit = new JButton(AfterGeneratedRecipeViewModel.SUBMIT_BUTTON_LABEL);
        favorite = new JButton(AfterGeneratedRecipeViewModel.ADD_TO_FAVORITE_LIST_BUTTON_LABEL);
        back = new JButton(AfterGeneratedRecipeViewModel.BACK_BUTTON_LABEL);
        buttons.add(submit);
        buttons.add(favorite);
        buttons.add(back);


        recipeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(recipeURL);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        submit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(submit)) {
                            AfterGeneratedRecipeState currentState = afterGeneratedRecipeViewModel.getState();
//                            System.out.println("Tipe makanan " + MealType.fromString(mealTypeInputField.getSelectedOption()));
//                            System.out.println("Hari " + fromStringToDayofWeek(dayInputField.getSelectedOption()));
                            currentState.setMealType(MealType.fromString(mealTypeInputField.getSelectedOption()));
                            currentState.setDayInPlanner(fromStringToDayofWeek(dayInputField.getSelectedOption()));
                            afterGeneratedRecipeViewModel.setState(currentState);
//                            System.out.println("Hari " + currentState.getMealType());
//                            System.out.println("Tipe makanan " + currentState.getMealType());

                            saveRecipeController.execute(
                                    currentState.getUsername(),
                                    currentState.getDayInPlanner(),
                                    currentState.getMealType(),
                                    currentState.getRecipe());
                        }
                    }
                }
        );

        favorite.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(favorite)) {
                            AfterGeneratedRecipeState currentState = afterGeneratedRecipeViewModel.getState();

                            addFavoriteRecipeController.execute(
                                    currentState.getUsername(),
                                    currentState.getRecipe().getLabel());
                        }
                    }
                }
        );
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            AfterGeneratedRecipeState currentState = afterGeneratedRecipeViewModel.getState();

                            GenerateRecipeState generateRecipeState = generateRecipeViewModel.getState();
                            generateRecipeState.setUsername(currentState.getUsername());

                            generateRecipeViewModel.setState(generateRecipeState);
                            generateRecipeViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(generateRecipeViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

//        mealTypeInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        AfterGeneratedRecipeState currentState = afterGeneratedRecipeViewModel.getState();
//                        String text = mealTypeInputField.getText();
//                        System.out.print("Ketikan " + text);
//                        currentState.setMealType(MealType.fromString(text));
//                        afterGeneratedRecipeViewModel.setState(currentState);
//                    }
//
//                    @Override
//                    public void keyPressed(KeyEvent e) {
//
//                    }
//
//                    @Override
//                    public void keyReleased(KeyEvent e) {
//
//                    }
//                }
//        );
//        dayInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        AfterGeneratedRecipeState currentState = afterGeneratedRecipeViewModel.getState();
//                        String text = dayInputField.getText();
//                        System.out.println(text);
//                        currentState.setDayInPlanner(fromStringToDayofWeek(text));
//                        afterGeneratedRecipeViewModel.setState(currentState);
//                    }
//
//                    @Override
//                    public void keyPressed(KeyEvent e) {
//
//                    }
//
//                    @Override
//                    public void keyReleased(KeyEvent e) {
//
//                    }
//                }
//        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(recipeLabel);
        this.add(servingsPanel);
        this.add(caloriesPanel);
        this.add(preparationPanel);
        this.add(addToPlannerPanel);
        this.add(buttons);
    }

    public static DayOfWeek fromStringToDayofWeek(String stringValue) {
//        System.out.println("Hasil ketikan: " + stringValue);
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (dayOfWeek.toString().equals(stringValue)) {
                return dayOfWeek;
            }
        }
        return null;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AfterGeneratedRecipeState state = (AfterGeneratedRecipeState) evt.getNewValue();
        // System.out.println("After view: " + state.getUsername());
        if (state.getRecipe() != null) {
            recipeLabel.setText(state.getRecipe().getLabel());
            try {
                recipeURL = new URI(state.getRecipe().getRecipeUrl());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            servings.setText(String.valueOf(state.getRecipe().getYield()));
            calories.setText(String.valueOf(state.getRecipe().getCalories()));
            preparation.setText(String.valueOf(state.getRecipe().getPreparationTime()));
        }
    }
}
