package view;

import interface_adapters.ViewManagerModel;
import interface_adapters.edit_profile.EditProfileController;
import interface_adapters.edit_profile.EditProfileState;
import interface_adapters.edit_profile.EditProfileViewModel;
import interface_adapters.my_profile.MyProfileController;
import interface_adapters.my_profile.MyProfileState;
import interface_adapters.my_profile.MyProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "my profile";
    private final EditProfileViewModel editProfileViewModel;
    private final MyProfileViewModel myProfileViewModel;
    private final EditProfileController editProfileController;
    private final MyProfileController myProfileController;
    private final ViewManagerModel viewManagerModel;
    JLabel username;
    JLabel name;
    JLabel age;
    JLabel gender;
    JLabel height;
    JLabel weight;
    JLabel calories;
    final JButton editProfile;
    final JButton back;

    public MyProfileView(ViewManagerModel viewManagerModel, MyProfileViewModel myProfileViewModel, EditProfileViewModel editProfileViewModel, MyProfileController myProfileController, EditProfileController editProfileController) {
        this.myProfileViewModel = myProfileViewModel;
        this.editProfileViewModel = editProfileViewModel;
        this.myProfileController = myProfileController;
        this.editProfileController = editProfileController;
        this.viewManagerModel = viewManagerModel;
        this.myProfileViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(MyProfileViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel(MyProfileViewModel.USERNAME_LABEL);
        username = new JLabel();

        JLabel nameInfo = new JLabel(MyProfileViewModel.NAME_LABEL);
        name = new JLabel();

        JLabel ageInfo = new JLabel(MyProfileViewModel.AGE_LABEL);
        age = new JLabel();

        JLabel genderInfo = new JLabel(MyProfileViewModel.GENDER_LABEL);
        gender = new JLabel();

        JLabel heightInfo = new JLabel(MyProfileViewModel.HEIGHT_LABEL);
        height = new JLabel();
        JLabel heightUnit = new JLabel(MyProfileViewModel.HEIGHT_UNIT);

        JLabel weightInfo = new JLabel(MyProfileViewModel.WEIGHT_LABEL);
        weight = new JLabel();
        JLabel weightUnit = new JLabel(MyProfileViewModel.WEIGHT_UNIT);

        JLabel caloriesInfo = new JLabel(MyProfileViewModel.CALORIES_LABEL);
        calories = new JLabel();
        JLabel caloriesUnit = new JLabel(MyProfileViewModel.CALORIES_UNIT);

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernamePanel.add(usernameInfo);
        usernamePanel.add(username);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(nameInfo);
        namePanel.add(name);

        JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        agePanel.add(ageInfo);
        agePanel.add(age);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(genderInfo);
        genderPanel.add(gender);

        JPanel heightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        heightPanel.add(heightInfo);
        heightPanel.add(height);
        heightPanel.add(heightUnit);

        JPanel weightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        weightPanel.add(weightInfo);
        weightPanel.add(weight);
        weightPanel.add(weightUnit);

        JPanel caloriesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        caloriesPanel.add(caloriesInfo);
        caloriesPanel.add(calories);
        caloriesPanel.add(caloriesUnit);

        JPanel buttons = new JPanel();
        editProfile = new JButton(MyProfileViewModel.EDIT_PROFILE_BUTTON);
        buttons.add(editProfile);

        back = new JButton("Back");
        buttons.add(back);

        editProfile.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(editProfile)) {
                            EditProfileState editProfileState = editProfileViewModel.getState();
                            MyProfileState myProfileState = myProfileViewModel.getState();

                            editProfileState.setUsername(myProfileState.getUsername());
                            editProfileState.setName(myProfileState.getName());
                            editProfileState.setAge(myProfileState.getAge());
                            editProfileState.setGender(myProfileState.getGender());
                            editProfileState.setHeight(myProfileState.getHeight());
                            editProfileState.setWeight(myProfileState.getWeight());
                            editProfileViewModel.setState(editProfileState);
                            editProfileViewModel.firePropertyChanged();

                            viewManagerModel.setActiveView(editProfileViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            viewManagerModel.setActiveView("logged in");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernamePanel);
        this.add(namePanel);
        this.add(agePanel);
        this.add(genderPanel);
        this.add(heightPanel);
        this.add(weightPanel);
        this.add(caloriesPanel);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MyProfileState state = (MyProfileState) evt.getNewValue();
        username.setText(state.getUsername());
        name.setText(state.getName());
        age.setText(String.valueOf(state.getAge()));
        gender.setText(state.getGender());
        height.setText(String.valueOf(state.getHeight()));
        weight.setText(String.valueOf(state.getWeight()));
        calories.setText(String.valueOf(state.getCalories()));
    }

    public MyProfileController getMyProfileController() {
        return myProfileController;
    }

    public EditProfileController getEditProfileController() {
        return editProfileController;
    }

    public JButton getBackButton() {
        return back;
    }
}
