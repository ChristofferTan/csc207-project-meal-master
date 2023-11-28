package view;

import interface_adapters.ViewManagerModel;
import interface_adapters.grocery_list.GroceryListState;
import interface_adapters.grocery_list.GroceryListViewModel;
import interface_adapters.logged_in.LoggedInState;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.grocery_list.GroceryListController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    JLabel username;
    final JButton groceryList;

    private final ViewManagerModel viewManagerModel;
    private final GroceryListViewModel groceryListViewModel;
    private final GroceryListController groceryListController;


    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, GroceryListViewModel groceryListViewModel, GroceryListController groceryListController) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        this.viewManagerModel = viewManagerModel;
        this.groceryListViewModel = groceryListViewModel;
        this.groceryListController = groceryListController;

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        groceryList = new JButton(loggedInViewModel.GROCERY_LIST_BUTTON_LABEL);
        buttons.add(groceryList);

        groceryList.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(groceryList)) {
                        LoggedInState currentState = loggedInViewModel.getState();
                        String username = currentState.getUsername();
                        groceryListController.execute(username);

                        viewManagerModel.setActiveView(groceryListViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();



                    }
                }
            }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
