//package view;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//
//import interface_adapters.edit_profile.EditProfileController;
//import interface_adapters.edit_profile.EditProfileState;
//import interface_adapters.edit_profile.EditProfileViewModel;
//import interface_adapters.generate_recipe.GenerateRecipeController;
//import interface_adapters.generate_recipe.GenerateRecipeState;
//import interface_adapters.generate_recipe.GenerateRecipeViewModel;
//
//// TODO: To be implemented later
//
//public class EditProfileView extends JPanel implements ActionListener, PropertyChangeListener {
//
//    public final String viewName = "generate recipe";
//    private final EditProfileViewModel editProfileViewModel;
//    private final JTextField nameInputField = new JTextField(15);
//    private final JTextField ageInputField = new JTextField(15);
//    private final JTextField genderInputField = new JTextField(15);
//    private final JTextField weightInputField = new JTextField(15);
//    private final JTextField heightInputField = new JTextField(15);
//    private final JTextField activityLevelInputField = new JTextField(15);
//    private final JTextArea generatedRecipeOutputField = new JTextArea(15, 15);
//
//    private final EditProfileController editProfileController;
//
//    private final JButton editProfile;
//
//    public EditProfileView(EditProfileController editProfileController, EditProfileViewModel editProfileViewModel) {
//        this.editProfileController = editProfileController;
//        this.editProfileViewModel = editProfileViewModel;
//        editProfileViewModel.addPropertyChangeListener(this);
//
//        JLabel title = new JLabel(GenerateRecipeViewModel.TITLE_LABEL);
//        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
//
//        LabelTextPanel nameInfo = new LabelTextPanel(
//                new JLabel(EditProfileViewModel.NAME_LABEL), nameInputField);
//        LabelTextPanel ageInfo = new LabelTextPanel(
//                new JLabel(EditProfileViewModel.AGE_LABEL), ageInputField);
//        LabelTextPanel genderInfo = new LabelTextPanel(
//                new JLabel(EditProfileViewModel.GENDER_LABEL), genderInputField);
//        LabelTextPanel weightInfo = new LabelTextPanel(
//                new JLabel(EditProfileViewModel.WEIGHT_LABEL), weightInputField);
//        LabelTextPanel heightInfo = new LabelTextPanel(
//                new JLabel(EditProfileViewModel.HEIGHT_LABEL), heightInputField);
//        LabelTextPanel activityLevelInfo = new LabelTextPanel(
//                new JLabel(EditProfileViewModel.ACTIVITY_LEVEL_LABEL), activityLevelInputField);
//
//        JPanel buttons = new JPanel();
//        editProfile = new JButton(EditProfileViewModel.EDIT_PROFILE_BUTTON_LABEL);
//        buttons.add(editProfile);
//
//        editProfile.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(editProfile)) {
//                            EditProfileState currentState = editProfileViewModel.getState();
//
//                            editProfileController.execute(
//                                    currentState.getKeyword(),
//                                    currentState.getDiet(),
//                                    currentState.getHealth(),
//                                    currentState.getCuisineType(),
//                                    currentState.getMealType(),
//                                    currentState.getMinCalories(),
//                                    currentState.getMaxCalories(),
//                                    currentState.getMaxPreparationTime()
//                            );
//                        }
//                    }
//                }
//        );
//
//        keywordInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
//                        String text = keywordInputField.getText() + e.getKeyChar();
//                        currentState.setKeyword(text);
//                        generateRecipeViewModel.setState(currentState);
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
//
//        dietInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
//                        String[] text = {dietInputField.getText() + e.getKeyChar()};
//                        currentState.setDiet(text);
//                        generateRecipeViewModel.setState(currentState);
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
//
//        healthInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
//                        String[] text = {healthInputField.getText() + e.getKeyChar()};
//                        currentState.setHealth(text);
//                        generateRecipeViewModel.setState(currentState);
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
//
//        cuisineTypeInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
//                        String[] text = {cuisineTypeInputField.getText() + e.getKeyChar()};
//                        currentState.setCuisineType(text);
//                        generateRecipeViewModel.setState(currentState);
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
//
//        mealTypeInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
//                        String[] text = {mealTypeInputField.getText() + e.getKeyChar()};
//                        currentState.setMealType(text);
//                        generateRecipeViewModel.setState(currentState);
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
//
//        minCaloriesInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
//                        String text = minCaloriesInputField.getText() + e.getKeyChar();
//                        currentState.setMinCalories(text);
//                        generateRecipeViewModel.setState(currentState);
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
//
//        maxCaloriesInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
//                        String text = maxCaloriesInputField.getText() + e.getKeyChar();
//                        currentState.setMaxCalories(text);
//                        generateRecipeViewModel.setState(currentState);
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
//
//        maxPreparationTimeInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        GenerateRecipeState currentState = generateRecipeViewModel.getState();
//                        String text = maxPreparationTimeInputField.getText() + e.getKeyChar();
//                        currentState.setMaxPreparationTime(text);
//                        generateRecipeViewModel.setState(currentState);
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
//
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        this.add(title);
//        this.add(keywordInfo);
//        this.add(dietInfo);
//        this.add(healthInfo);
//        this.add(cuisineTypeInfo);
//        this.add(mealTypeInfo);
//        this.add(minCaloriesInfo);
//        this.add(maxCaloriesInfo);
//        this.add(maxPreparationTimeInfo);
//        this.add(buttons);
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent evt) {
//
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        GenerateRecipeState state = (GenerateRecipeState) evt.getNewValue();
//    }
//}
