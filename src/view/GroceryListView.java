package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapters.ViewManagerModel;
import interface_adapters.grocery_list.GroceryListController;
import interface_adapters.grocery_list.GroceryListState;
import interface_adapters.grocery_list.GroceryListViewModel;

public class GroceryListView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "grocery list";
    private final GroceryListViewModel groceryListViewModel;
    private final JList grocery;
    final JButton back;
    private final GroceryListController groceryListController;
    private final ViewManagerModel viewManagerModel;

    public GroceryListView(GroceryListController groceryListController, GroceryListViewModel groceryListViewModel, ViewManagerModel viewManagerModel) {
        this.groceryListController = groceryListController;
        this.groceryListViewModel = groceryListViewModel;
        this.viewManagerModel = viewManagerModel;
        this.groceryListViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Grocery List");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel groceryInfo = new JLabel("Grocery List: ");

        grocery = new JList();

        JPanel buttons = new JPanel();
        back = new JButton(groceryListViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        back.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(back)) {

                        viewManagerModel.setActiveView("logged in");
                        viewManagerModel.firePropertyChanged();
                    }
                }
            }
        );

        back.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        //this.add(groceryInfo);
        this.add(grocery);
        this.add(buttons);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GroceryListState state = (GroceryListState) evt.getNewValue();
        grocery.setListData(state.getGroceryList());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public GroceryListController getGroceryListController() {
        return groceryListController;
    }
}

