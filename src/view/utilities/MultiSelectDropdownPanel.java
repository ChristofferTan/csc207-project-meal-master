package view.utilities;

import interface_adapters.generate_recipe.GenerateRecipeState;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MultiSelectDropdownPanel extends JPanel {

    private DefaultListModel<String> listModel;
    private JList<String> optionsList;
    private JTextField textField;
    private GenerateRecipeState currentState;
    private String label;

    public MultiSelectDropdownPanel(String labelText, String[] options, GenerateRecipeState currentState, String label) {
        this.currentState = currentState;
        this.label = label;

        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        for (String option : options) {
            listModel.addElement(option);
        }

        optionsList = new JList<>(listModel);
        optionsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(optionsList);

        textField = new JTextField(20);
        textField.setEditable(false);

        JButton showDropdownButton = new JButton("Show Dropdown");
        showDropdownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedValues = showDropdownDialog();
                if (selectedValues != null) {
                    textField.setText(String.join(", ", selectedValues));
                }
            }
        });

        JPanel innerPanel = new JPanel(new BorderLayout());
        innerPanel.add(textField, BorderLayout.CENTER);
        innerPanel.add(showDropdownButton, BorderLayout.EAST);

        add(new JLabel(labelText, JLabel.CENTER), BorderLayout.NORTH);
        add(innerPanel, BorderLayout.CENTER);
    }

    private List<String> showDropdownDialog() {
        JFrame parentFrame = new JFrame();

        JDialog dialog = new JDialog(parentFrame, "Select Options", true);
        dialog.setLayout(new BorderLayout());

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected values
                List<String> selectedValues = optionsList.getSelectedValuesList();
                dialog.dispose();
                // Return the selected values to the calling method
                handleSelectedValuesForGenerateRecipeView(selectedValues, currentState, label);
            }
        });

        dialog.add(new JScrollPane(optionsList), BorderLayout.CENTER);
        dialog.add(selectButton, BorderLayout.SOUTH);

        dialog.setSize(200, 200);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);

        // Return null if the dialog was closed without making a selection
        return optionsList.getSelectedValuesList();
    }

    private void handleSelectedValuesForGenerateRecipeView(List<String> selectedValues, GenerateRecipeState currentState, String label) {
        // Handle the selected values here
        if (label == GenerateRecipeViewModel.DIET_LABEL) {
            currentState.setDiet(selectedValues.toArray(new String[0]));
        } else if (label == GenerateRecipeViewModel.HEALTH_LABEL) {
            currentState.setHealth(selectedValues.toArray(new String[0]));
        } else if (label == GenerateRecipeViewModel.CUISINE_TYPE_LABEL) {
            currentState.setCuisineType(selectedValues.toArray(new String[0]));
        } else if (label == GenerateRecipeViewModel.MEAL_TYPE_LABEL) {
            currentState.setMealType(selectedValues.toArray(new String[0]));
        }
    }
}
