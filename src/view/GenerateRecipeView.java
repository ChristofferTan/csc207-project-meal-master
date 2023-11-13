package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import interface_adapters.generate_recipe.GenerateRecipeController;
import interface_adapters.generate_recipe.GenerateRecipeState;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;

public class GenerateRecipeView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "generate recipe";
    private final GenerateRecipeViewModel generateRecipeViewModel;
    private final JTextField keywordInputField = new JTextField(15);
    private final JTextField dietInputField = new JTextField(15);
    private final JTextField healthInputField = new JTextField(15);
    private final JTextField cuisineTypeInputField = new JTextField(15);
    private final JTextField mealTypeInputField = new JTextField(15);
    private final JTextField minCaloriesInputField = new JTextField(15);
    private final JTextField maxCaloriesInputField = new JTextField(15);
    private final JTextField maxPreparationTimeInputField = new JTextField(15);
    private final JTextArea generatedRecipeOutputField = new JTextArea(15, 15);

    private final GenerateRecipeController generateRecipeController;

    private final JButton generateRecipe;

    public GenerateRecipeView(GenerateRecipeController generateRecipeController, GenerateRecipeViewModel generateRecipeViewModel) {
        this.generateRecipeController = generateRecipeController;
        this.generateRecipeViewModel = generateRecipeViewModel;
        generateRecipeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(GenerateRecipeViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        LabelTextPanel keywordInfo = new LabelTextPanel(
                new JLabel(GenerateRecipeViewModel.KEYWORD_LABEL), keywordInputField);
        LabelTextPanel dietInfo = new LabelTextPanel(
                new JLabel(GenerateRecipeViewModel.DIET_LABEL), dietInputField);
        LabelTextPanel healthInfo = new LabelTextPanel(
                new JLabel(GenerateRecipeViewModel.HEALTH_LABEL), healthInputField);
        LabelTextPanel cuisineTypeInfo = new LabelTextPanel(
                new JLabel(GenerateRecipeViewModel.CUISINE_TYPE_LABEL), cuisineTypeInputField);
        LabelTextPanel mealTypeInfo = new LabelTextPanel(
                new JLabel(GenerateRecipeViewModel.MEAL_TYPE_LABEL), mealTypeInputField);
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

        dietInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
                        String[] text = {dietInputField.getText() + e.getKeyChar()};
                        currentState.setDiet(text);
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

        healthInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
                        String[] text = {healthInputField.getText() + e.getKeyChar()};
                        currentState.setHealth(text);
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

        cuisineTypeInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
                        String[] text = {cuisineTypeInputField.getText() + e.getKeyChar()};
                        currentState.setCuisineType(text);
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

        mealTypeInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
                        String[] text = {mealTypeInputField.getText() + e.getKeyChar()};
                        currentState.setMealType(text);
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
        this.add(dietInfo);
        this.add(healthInfo);
        this.add(cuisineTypeInfo);
        this.add(mealTypeInfo);
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

