package view;

import interface_adapters.show_planner.ShowPlannerState;
import interface_adapters.show_planner.ShowPlannerViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShowPlannerView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "show planner";
    private final ShowPlannerViewModel showPlannerViewModel;


    /**
     * A window with a title and a JButton.
     */
    public ShowPlannerView(ShowPlannerViewModel showPlannerViewModel) {
        this.showPlannerViewModel = showPlannerViewModel;
        this.showPlannerViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Show Planner Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // TODO: show planner and add buttons

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
//        this.add(plannerInfo); TODO
//        this.add(planner);
//        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ShowPlannerState state = (ShowPlannerState) evt.getNewValue();
        // TODO: planner.setText(state.getPlanner());
    }
}
