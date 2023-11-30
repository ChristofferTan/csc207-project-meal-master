package view;

import interface_adapters.ViewManagerModel;
import interface_adapters.grocery_list.GroceryListState;
import interface_adapters.grocery_list.GroceryListViewModel;
import interface_adapters.logged_in.LoggedInState;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.grocery_list.GroceryListController;
import interface_adapters.my_planner.MyPlannerController;
import interface_adapters.myprofile.MyProfileController;
import interface_adapters.myprofile.MyProfileViewModel;

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
    final JButton generateRecipe;
    final JButton groceryList;
    final JButton myProfile;

    final JButton myPlanner;

    final JButton logout;

    private final ViewManagerModel viewManagerModel;

    private final GroceryListController groceryListController;

    private final MyProfileController myProfileController;
    //private final MyProfileViewModel myProfileViewModel;

    private final MyPlannerController myPlannerController;


    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, GroceryListController groceryListController,
                        MyProfileController myProfileController, MyPlannerController myPlannerController) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        this.viewManagerModel = viewManagerModel;
        this.groceryListController = groceryListController;

        this.myProfileController = myProfileController;

        this.myPlannerController = myPlannerController;

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Welcome! You are logged in as:");
        username = new JLabel();

        JPanel buttons = new JPanel();

        myProfile = new JButton(loggedInViewModel.MY_PROFILE);
        buttons.add(myProfile);

        generateRecipe = new JButton(loggedInViewModel.GENERATE_RECIPE_BUTTON_LABEL);
        buttons.add(generateRecipe);

        myPlanner = new JButton(loggedInViewModel.MY_PLANNER);
        buttons.add(myPlanner);

        groceryList = new JButton(loggedInViewModel.GROCERY_LIST_BUTTON_LABEL);
        buttons.add(groceryList);

        logout = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logout);

        myProfile.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(myProfile)) {
                        LoggedInState currentState = loggedInViewModel.getState();
                        String username = currentState.getUsername();
                        myProfileController.execute(username);

                        viewManagerModel.setActiveView("my profile");
                        viewManagerModel.firePropertyChanged();
                    }
                }
            }
        );

        generateRecipe.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(generateRecipe)) {
                        LoggedInState currentState = loggedInViewModel.getState();
                        String username = currentState.getUsername();
                        viewManagerModel.setActiveView("generate recipe");
                        viewManagerModel.firePropertyChanged();
                    }
                }
            }
        );

        myPlanner.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(myPlanner)) {
                        LoggedInState currentState = loggedInViewModel.getState();
                        String username = currentState.getUsername();
                        myPlannerController.execute(username);

                        viewManagerModel.setActiveView("my planner");
                        viewManagerModel.firePropertyChanged();
                    }
                }
            }
        );

        groceryList.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(groceryList)) {
                        LoggedInState currentState = loggedInViewModel.getState();
                        String username = currentState.getUsername();
                        groceryListController.execute(username);

                        viewManagerModel.setActiveView("grocery list");
                        viewManagerModel.firePropertyChanged();
                    }
                }
            }
        );

        logout.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(logout)) {
                        viewManagerModel.setActiveView("log in");
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
