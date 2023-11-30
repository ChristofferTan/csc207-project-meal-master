package view;

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
import interface_adapters.myprofile.MyProfileViewModel;

public class EditProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "edit profile";
    private final EditProfileViewModel editProfileViewModel;
    private final JTextField nameInputField = new JTextField(15);
    private final JTextField ageInputField = new JTextField(15);
    private final JTextField genderInputField = new JTextField(15);
    private final JTextField weightInputField = new JTextField(15);
    private final JTextField heightInputField = new JTextField(15);
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
        LabelTextPanel ageInfo = new LabelTextPanel(
                new JLabel(EditProfileViewModel.AGE_LABEL), ageInputField);
        LabelTextPanel genderInfo = new LabelTextPanel(
                new JLabel(EditProfileViewModel.GENDER_LABEL), genderInputField);
        LabelTextPanel weightInfo = new LabelTextPanel(
                new JLabel(EditProfileViewModel.WEIGHT_LABEL), weightInputField);
        LabelTextPanel heightInfo = new LabelTextPanel(
                new JLabel(EditProfileViewModel.HEIGHT_LABEL), heightInputField);
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

        ageInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EditProfileState currentState = editProfileViewModel.getState();
                        currentState.setAge(Integer.parseInt(ageInputField.getText() + e.getKeyChar()));
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

        genderInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EditProfileState currentState = editProfileViewModel.getState();
                        String text = genderInputField.getText();
                        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                            if (!text.isEmpty()) {
                                text = text.substring(0, text.length() - 1);
                            }
                        }
                        else {
                            text += e.getKeyChar();
                        }
                        currentState.setGender(text);
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

        weightInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EditProfileState currentState = editProfileViewModel.getState();
                        currentState.setWeight(Integer.parseInt(weightInputField.getText() + e.getKeyChar()));
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

        heightInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EditProfileState currentState = editProfileViewModel.getState();
                        currentState.setHeight(Integer.parseInt(heightInputField.getText() + e.getKeyChar()));
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(nameInfo);
        this.add(ageInfo);
        this.add(genderInfo);
        this.add(weightInfo);
        this.add(heightInfo);
        this.add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EditProfileState state = (EditProfileState) evt.getNewValue();
        // System.out.println("Property berubah " + state.getName());
        setFields(state);
    }
    private void setFields(EditProfileState state) {
        nameInputField.setText(state.getName());
        genderInputField.setText(state.getGender());
    }
}
