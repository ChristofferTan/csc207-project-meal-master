package view;

import javax.accessibility.AccessibleEditableText;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapters.ViewManagerModel;
import interface_adapters.edit_profile.EditProfileController;
import interface_adapters.edit_profile.EditProfileState;
import interface_adapters.edit_profile.EditProfileViewModel;
import interface_adapters.myprofile.MyProfileController;
import interface_adapters.myprofile.MyProfileState;
import interface_adapters.myprofile.MyProfileViewModel;
import interface_adapters.signup.SignupState;
import interface_adapters.signup.SignupViewModel;

public class EditProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "edit profile";
    private final EditProfileViewModel editProfileViewModel;
    private final JTextField nameInputField = new JTextField(15);
    private final JComboBox<Integer> ageComboBox, weightComboBox, heightComboBox;
    private final JComboBox<String> genderComboBox;
    private final JTextArea editedProfileOutputField = new JTextArea(15, 15);

    private final EditProfileController editProfileController;
    private final MyProfileController myProfileController;
    private final MyProfileViewModel myProfileViewModel;
    private final ViewManagerModel viewManagerModel;

    private final JButton editProfile;

    public EditProfileView(EditProfileController editProfileController, EditProfileViewModel editProfileViewModel, MyProfileController myProfileController, MyProfileViewModel myProfileViewModel, ViewManagerModel viewManagerModel) {
        this.editProfileController = editProfileController;
        this.editProfileViewModel = editProfileViewModel;
        this.myProfileController = myProfileController;
        this.myProfileViewModel = myProfileViewModel;
        this.viewManagerModel = viewManagerModel;

        editProfileViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(editProfileViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        LabelTextPanel nameInfo = new LabelTextPanel(
                new JLabel(EditProfileViewModel.NAME_LABEL), nameInputField);

        JPanel ageDropdownPanel = new JPanel();
        JPanel genderDropdownPanel = new JPanel();
        JPanel weightDropdownPanel = new JPanel();
        JPanel heightDropdownPanel = new JPanel();

        ageDropdownPanel.add(new JLabel(EditProfileViewModel.AGE_LABEL));
        genderDropdownPanel.add(new JLabel(EditProfileViewModel.GENDER_LABEL));
        weightDropdownPanel.add(new JLabel(EditProfileViewModel.WEIGHT_LABEL));
        heightDropdownPanel.add(new JLabel(EditProfileViewModel.HEIGHT_LABEL));

        genderComboBox = new JComboBox<>(new String[]{"Man", "Woman", "Prefer not to say"});

        Integer ageRange[] = new Integer[101];
        Integer weightRange[] = new Integer[250];
        Integer heighRange[] = new Integer[250];

        for (int i=0;i<=100;i++) ageRange[i] = i;
        for (int i=10;i<=200;i++) weightRange[i] = i;
        for (int i=10;i<=200;i++) heighRange[i] = i;

        ageComboBox = new JComboBox<>(ageRange);
        weightComboBox = new JComboBox<>(weightRange);
        heightComboBox = new JComboBox<>(heighRange);

        genderDropdownPanel.add(genderComboBox);
        ageDropdownPanel.add(ageComboBox);
        heightDropdownPanel.add(heightComboBox);
        weightDropdownPanel.add(weightComboBox);

        JPanel buttons = new JPanel();
        editProfile = new JButton(EditProfileViewModel.EDIT_PROFILE_BUTTON_LABEL);
        buttons.add(editProfile);

        editProfile.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(editProfile)) {
                            EditProfileState currentState = editProfileViewModel.getState();
                            // System.out.println("state: " + currentState.getUsername());

                            editProfileController.execute(
                                    currentState.getUsername(),
                                    currentState.getName(),
                                    currentState.getAge(),
                                    currentState.getGender(),
                                    currentState.getWeight(),
                                    currentState.getHeight()
                            );
//                            currentState = editProfileViewModel.getState();
//
//                            System.out.println(currentState.getUsername());
                            myProfileController.execute(currentState.getUsername());
                            myProfileViewModel.firePropertyChanged();
//
                            viewManagerModel.setActiveView(myProfileViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        nameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EditProfileState currentState = editProfileViewModel.getState();
                        String text = nameInputField.getText();
                        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                            if (!text.isEmpty()) {
                                text = text.substring(0, text.length() - 1);
                            }
                        }
                        else {
                            text += e.getKeyChar();
                        }
                        currentState.setName(text);
                        editProfileViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        ageComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditProfileState editProfileState = editProfileViewModel.getState();
                int selectedValue = (int) ageComboBox.getSelectedItem();
                editProfileState.setAge(selectedValue);
                editProfileViewModel.setState(editProfileState);
            }
        });

        genderComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditProfileState editProfileState = editProfileViewModel.getState();
                String selectedValue = (String) genderComboBox.getSelectedItem();
                editProfileState.setGender(selectedValue);
                editProfileViewModel.setState(editProfileState);
            }
        });

        heightComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditProfileState editProfileState = editProfileViewModel.getState();
                int selectedValue = (int) heightComboBox.getSelectedItem();
                editProfileState.setHeight(selectedValue);
                editProfileViewModel.setState(editProfileState);
            }
        });

        weightComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditProfileState editProfileState = editProfileViewModel.getState();
                int selectedValue = (int) weightComboBox.getSelectedItem();
                editProfileState.setWeight(selectedValue);
                editProfileViewModel.setState(editProfileState);

            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(nameInfo);
        this.add(ageDropdownPanel);
        this.add(genderDropdownPanel);
        this.add(weightDropdownPanel);
        this.add(heightDropdownPanel);
        this.add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EditProfileState state = (EditProfileState) evt.getNewValue();

        ageComboBox.setSelectedIndex(state.getAge());
        heightComboBox.setSelectedIndex(state.getHeight());
        weightComboBox.setSelectedIndex(state.getWeight());

        if (state.getGender().equals("Man")) {
            ageComboBox.setSelectedIndex(0);
        } else if (state.getGender().equals("Woman")) {
            ageComboBox.setSelectedIndex(1);
        } else {
            ageComboBox.setSelectedIndex(2);
        }
        nameInputField.setText(state.getName());
    }
}
