package view;

import data_access.FileRecipeDataAccessObject;
import entity.MealType;
import interface_adapters.ViewManagerModel;
import interface_adapters.after_generated_recipe.AfterGeneratedRecipeViewModel;
import interface_adapters.delete_favorite_recipe.DeleteFavoriteRecipeController;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeController;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeState;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeViewModel;
import interface_adapters.save_recipe.SaveRecipeController;
import view.utilities.SingleSelectDropdownPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.DayOfWeek;

public class MyFavoriteRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "my favorite recipe";
    private final MyFavoriteRecipeViewModel myFavoriteRecipeViewModel;
    private final MyFavoriteRecipeController myFavoriteRecipeController;
    private final SaveRecipeController saveRecipeController;
    private final DeleteFavoriteRecipeController deleteFavoriteRecipeController;
    private final ViewManagerModel viewManagerModel;
    private final FileRecipeDataAccessObject fileRecipeDataAccessObject;
    private final JList favoriteRecipes;
    private final SingleSelectDropdownPanel mealTypeInputField;
    private final SingleSelectDropdownPanel dayInputField;
    private final JButton submit;
    private final JButton show;
    private final JButton delete;
    private final JButton back;

    public MyFavoriteRecipeView(MyFavoriteRecipeController myFavoriteRecipeController, MyFavoriteRecipeViewModel myFavoriteRecipeViewModel,
                                SaveRecipeController saveRecipeController, DeleteFavoriteRecipeController deleteFavoriteRecipeController,
                                ViewManagerModel viewManagerModel, FileRecipeDataAccessObject fileRecipeDataAccessObject) {
        this.myFavoriteRecipeController = myFavoriteRecipeController;
        this.myFavoriteRecipeViewModel = myFavoriteRecipeViewModel;
        this.saveRecipeController = saveRecipeController;
        this.deleteFavoriteRecipeController = deleteFavoriteRecipeController;
        this.viewManagerModel = viewManagerModel;
        this.fileRecipeDataAccessObject = fileRecipeDataAccessObject;
        myFavoriteRecipeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(myFavoriteRecipeViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        favoriteRecipes = new JList();
        favoriteRecipes.setListData(myFavoriteRecipeViewModel.getState().getFavoriteRecipes());

        JPanel buttons = new JPanel();
        submit = new JButton(MyFavoriteRecipeViewModel.SUBMIT_LABEL);
        buttons.add(submit);
        show = new JButton(MyFavoriteRecipeViewModel.SHOW_LABEL);
        buttons.add(show);
        delete = new JButton(MyFavoriteRecipeViewModel.DELETE_LABEL);
        buttons.add(delete);
        back = new JButton(MyFavoriteRecipeViewModel.BACK_LABEL);
        buttons.add(back);

        JLabel addToPlannerInfo = new JLabel(AfterGeneratedRecipeViewModel.ADD_TO_PLANNER_LABEL);
        JLabel dayInPlannerInfo = new JLabel(AfterGeneratedRecipeViewModel.ADD_TO_PLANNER_DAY_LABEL);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        submit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(submit)) {
                            if (favoriteRecipes.getSelectedIndex() != -1) {
                                MyFavoriteRecipeState currentState = myFavoriteRecipeViewModel.getState();
                                currentState.setMealType(MealType.fromString(mealTypeInputField.getSelectedOption()));
                                currentState.setDayInPlanner(fromStringToDayofWeek(dayInputField.getSelectedOption()));
                                myFavoriteRecipeViewModel.setState(currentState);

                                String label = (String)favoriteRecipes.getSelectedValue();

                                saveRecipeController.execute(
                                        currentState.getUsername(),
                                        currentState.getDayInPlanner(),
                                        currentState.getMealType(),
                                        fileRecipeDataAccessObject.getRecipe(label));
                            } else {
                                JOptionPane.showConfirmDialog(null, "Please select a recipe", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    }
                }
        );

        show.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(show)) {
                            if (favoriteRecipes.getSelectedIndex() != -1) {
                                String label = (String)favoriteRecipes.getSelectedValue();

                                URI recipeURL;

                                try {
                                    recipeURL = new URI(fileRecipeDataAccessObject.getRecipe(label).getRecipeUrl());
                                } catch (URISyntaxException e) {
                                    throw new RuntimeException(e);
                                }

                                try {
                                    Desktop.getDesktop().browse(recipeURL);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                JOptionPane.showConfirmDialog(null, "Please select a recipe", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    }
                }
        );

        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(delete)) {
                            if (favoriteRecipes.getSelectedIndex() != -1) {
                                String username = myFavoriteRecipeViewModel.getState().getUsername();
                                deleteFavoriteRecipeController.execute(username, (String)favoriteRecipes.getSelectedValue());
                                myFavoriteRecipeController.execute(username);
                            } else {
                                JOptionPane.showConfirmDialog(null, "Please select a recipe", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    }
                }
        );

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            if (evt.getSource().equals(back)) {
                                viewManagerModel.setActiveView("logged in");
                                viewManagerModel.firePropertyChanged();
                            }
                        }
                    }
                }
        );

        mealTypeInputField = new SingleSelectDropdownPanel(null, MyFavoriteRecipeViewModel.MEAL_TYPE_OPTIONS);
        dayInputField = new SingleSelectDropdownPanel(null, MyFavoriteRecipeViewModel.DAY_OPTIONS);
        JPanel addToPlannerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addToPlannerPanel.add(addToPlannerInfo);
        addToPlannerPanel.add(mealTypeInputField);
        addToPlannerPanel.add(dayInPlannerInfo);
        addToPlannerPanel.add(dayInputField);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(new JScrollPane(favoriteRecipes));
        this.add(addToPlannerPanel);
        this.add(buttons);
    }

    public static DayOfWeek fromStringToDayofWeek(String stringValue) {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (dayOfWeek.toString().equals(stringValue)) {
                return dayOfWeek;
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MyFavoriteRecipeState state = (MyFavoriteRecipeState) evt.getNewValue();
        if (state.getLabelError() != null) {
            JOptionPane.showConfirmDialog(null, state.getLabelError(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
        favoriteRecipes.setListData(state.getFavoriteRecipes());
    }

    public MyFavoriteRecipeController getMyFavoriteRecipeController() {
        return myFavoriteRecipeController;
    }
}
