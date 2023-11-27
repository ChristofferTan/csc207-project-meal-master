package view;

import interface_adapters.generate_recipe.GenerateRecipeController;
import interface_adapters.generate_recipe.GenerateRecipeState;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import view.utilities.MultiSelectDropdownPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GenerateRecipeView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "generate recipe";
    private final GenerateRecipeViewModel generateRecipeViewModel;
    private final JTextField keywordInputField = new JTextField(15);

    // Replace input fields with MultiSelectDropdownPanels
    private final MultiSelectDropdownPanel dietPanel;
    private final MultiSelectDropdownPanel healthPanel;
    private final MultiSelectDropdownPanel cuisineTypePanel;
    private final MultiSelectDropdownPanel mealTypePanel;

    private final JTextField minCaloriesInputField = new JTextField(15);
    private final JTextField maxCaloriesInputField = new JTextField(15);
    private final JTextField maxPreparationTimeInputField = new JTextField(15);
    private final JTextArea generatedRecipeOutputField = new JTextArea(15, 15);

    private final GenerateRecipeController generateRecipeController;

    private final JButton generateRecipe;

    public GenerateRecipeView(GenerateRecipeController generateRecipeController, GenerateRecipeViewModel generateRecipeViewModel) {
        this.generateRecipeController = generateRecipeController;
        this.generateRecipeViewModel = generateRecipeViewModel;

        dietPanel = new MultiSelectDropdownPanel("Diet", GenerateRecipeViewModel.DIET_OPTIONS, generateRecipeViewModel.getState(), GenerateRecipeViewModel.DIET_LABEL);
        healthPanel = new MultiSelectDropdownPanel("Health", GenerateRecipeViewModel.HEALTH_OPTIONS, generateRecipeViewModel.getState(), GenerateRecipeViewModel.HEALTH_LABEL);
        cuisineTypePanel = new MultiSelectDropdownPanel("Cuisine Type", GenerateRecipeViewModel.CUISINE_TYPE_OPTIONS, generateRecipeViewModel.getState(), GenerateRecipeViewModel.CUISINE_TYPE_LABEL);
        mealTypePanel = new MultiSelectDropdownPanel("Meal Type", GenerateRecipeViewModel.MEAL_TYPE_OPTIONS, generateRecipeViewModel.getState(), GenerateRecipeViewModel.MEAL_TYPE_LABEL);

        generateRecipeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(GenerateRecipeViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        LabelTextPanel keywordInfo = new LabelTextPanel(
                new JLabel(GenerateRecipeViewModel.KEYWORD_LABEL), keywordInputField);
        LabelTextPanel minCaloriesInfo = new LabelTextPanel(
                new JLabel(GenerateRecipeViewModel.MIN_CALORIES_LABEL), minCaloriesInputField);
        LabelTextPanel maxCaloriesInfo = new LabelTextPanel(
                new JLabel(GenerateRecipeViewModel.MAX_CALORIES_LABEL), maxCaloriesInputField);
        LabelTextPanel maxPreparationTimeInfo = new LabelTextPanel(
                new JLabel(GenerateRecipeViewModel.MAX_PREPARATION_TIME_LABEL), maxPreparationTimeInputField);

        JPanel buttons = new JPanel();
        generateRecipe = new JButton(GenerateRecipeViewModel.GENERATE_RECIPE_BUTTON_LABEL);
        buttons.add(generateRecipe);

        generateRecipe.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(generateRecipe)) {
                            GenerateRecipeState currentState = generateRecipeViewModel.getState();

                            generateRecipeController.execute(
                                    currentState.getKeyword(),
                                    currentState.getDiet(),
                                    currentState.getHealth(),
                                    currentState.getCuisineType(),
                                    currentState.getMealType(),
                                    currentState.getMinCalories(),
                                    currentState.getMaxCalories(),
                                    currentState.getMaxPreparationTime()
                            );
                        }
                    }
                }
        );

        keywordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
                        String text = keywordInputField.getText() + e.getKeyChar();
                        currentState.setKeyword(text);
                        generateRecipeViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        minCaloriesInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
                        String text = minCaloriesInputField.getText() + e.getKeyChar();
                        currentState.setMinCalories(text);
                        generateRecipeViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        maxCaloriesInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
                        String text = maxCaloriesInputField.getText() + e.getKeyChar();
                        currentState.setMaxCalories(text);
                        generateRecipeViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        maxPreparationTimeInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
                        String text = maxPreparationTimeInputField.getText() + e.getKeyChar();
                        currentState.setMaxPreparationTime(text);
                        generateRecipeViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(keywordInfo);

        // Add MultiSelectDropdownPanels
        this.add(dietPanel);
        this.add(healthPanel);
        this.add(cuisineTypePanel);
        this.add(mealTypePanel);

        this.add(minCaloriesInfo);
        this.add(maxCaloriesInfo);
        this.add(maxPreparationTimeInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GenerateRecipeState state = (GenerateRecipeState) evt.getNewValue();
    }
}
