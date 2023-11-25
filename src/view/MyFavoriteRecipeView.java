package view;

import entity.Recipe;
import interface_adapters.delete_favorite_recipe.DeleteFavoriteRecipeController;
import interface_adapters.delete_favorite_recipe.DeleteFavoriteRecipeState;
import interface_adapters.delete_favorite_recipe.DeleteFavoriteRecipeViewModel;
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
    private final DeleteFavoriteRecipeViewModel deleteFavoriteRecipeViewModel;
    private final DeleteFavoriteRecipeController deleteFavoriteRecipeController;
    private final JList favoriteRecipes;
    private final JButton delete;

    public MyFavoriteRecipeView(MyFavoriteRecipeController myFavoriteRecipeController, MyFavoriteRecipeViewModel myFavoriteRecipeViewModel,
                                DeleteFavoriteRecipeController deleteFavoriteRecipeController, DeleteFavoriteRecipeViewModel deleteFavoriteRecipeViewModel) {
        this.myFavoriteRecipeController = myFavoriteRecipeController;
        this.myFavoriteRecipeViewModel = myFavoriteRecipeViewModel;
        this.deleteFavoriteRecipeController = deleteFavoriteRecipeController;
        this.deleteFavoriteRecipeViewModel = deleteFavoriteRecipeViewModel;
        myFavoriteRecipeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(myFavoriteRecipeViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        favoriteRecipes = new JList();

        JPanel buttons = new JPanel();
        delete = new JButton(MyFavoriteRecipeViewModel.DELETE_LABEL);
        buttons.add(delete);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(delete)) {
                            if (favoriteRecipes.getSelectedIndex() != -1) {
                                DeleteFavoriteRecipeState currentState = deleteFavoriteRecipeViewModel.getState();
                                deleteFavoriteRecipeController.execute(currentState.getUsername(), (String)favoriteRecipes.getSelectedValue());
                            }
                        }
                    }
                }
        );

        this.add(title);
        this.add(favoriteRecipes);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MyFavoriteRecipeState state = (MyFavoriteRecipeState) evt.getNewValue();
        favoriteRecipes.setListData(state.getFavoriteRecipes().toArray());
    }
}
