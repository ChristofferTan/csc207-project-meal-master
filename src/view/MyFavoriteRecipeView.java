package view;

import interface_adapters.ViewManagerModel;
import interface_adapters.delete_favorite_recipe.DeleteFavoriteRecipeController;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeController;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeState;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyFavoriteRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "my favorite recipe";
    private final MyFavoriteRecipeViewModel myFavoriteRecipeViewModel;
    private final MyFavoriteRecipeController myFavoriteRecipeController;
    private final DeleteFavoriteRecipeController deleteFavoriteRecipeController;
    private final ViewManagerModel viewManagerModel;
    private final JList favoriteRecipes;
    private final JButton delete;
    private final JButton back;

    public MyFavoriteRecipeView(MyFavoriteRecipeController myFavoriteRecipeController, MyFavoriteRecipeViewModel myFavoriteRecipeViewModel,
                                DeleteFavoriteRecipeController deleteFavoriteRecipeController, ViewManagerModel viewManagerModel) {
        this.myFavoriteRecipeController = myFavoriteRecipeController;
        this.myFavoriteRecipeViewModel = myFavoriteRecipeViewModel;
        this.deleteFavoriteRecipeController = deleteFavoriteRecipeController;
        this.viewManagerModel = viewManagerModel;
        myFavoriteRecipeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(myFavoriteRecipeViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        favoriteRecipes = new JList();
        favoriteRecipes.setListData(myFavoriteRecipeViewModel.getState().getFavoriteRecipes());

        JPanel buttons = new JPanel();
        delete = new JButton(MyFavoriteRecipeViewModel.DELETE_LABEL);
        buttons.add(delete);
        back = new JButton(MyFavoriteRecipeViewModel.BACK_LABEL);
        buttons.add(back);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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

        this.add(title);
        this.add(new JScrollPane(favoriteRecipes));
        this.add(buttons);
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
