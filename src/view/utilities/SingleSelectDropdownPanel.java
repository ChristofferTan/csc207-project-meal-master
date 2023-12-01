package view.utilities;

import javax.swing.*;
import java.awt.*;

public class SingleSelectDropdownPanel extends JPanel {

    private JComboBox<String> comboBox;
    private JLabel label;

    public SingleSelectDropdownPanel(String labelText, String[] dropdownOptions) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        comboBox = new JComboBox<>(dropdownOptions);

        if (labelText != null && !labelText.isEmpty()) {
            label = new JLabel(labelText);
            add(label);
            add(Box.createRigidArea(new Dimension(10, 0)));  // Add spacing between label and component
        }

        add(comboBox);
    }

    public String getSelectedOption() {
        return (String) comboBox.getSelectedItem();
    }

    public void setSelectedOption(String option) {
        comboBox.setSelectedItem(option);
    }
}
