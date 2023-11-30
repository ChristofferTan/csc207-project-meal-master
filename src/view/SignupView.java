package view;

import interface_adapters.ViewManagerModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupController;
import interface_adapters.signup.SignupState;
import interface_adapters.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JTextField nameInputField = new JTextField(15);
    private final JPanel ageDropdownPanel;
    private final JPanel heightDropdownPanel;
    private final JPanel weightDropdownPanel;
    private final JPanel genderDropdownPanel;
    private final SignupController signupController;

    private final JButton signUp, logIn;

    public SignupView(SignupController signUpController, SignupViewModel signupViewModel, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {

        this.signupController = signUpController;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        signupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        ageDropdownPanel = new JPanel();
        genderDropdownPanel = new JPanel();
        weightDropdownPanel = new JPanel();
        heightDropdownPanel = new JPanel();

        ageDropdownPanel.add(new JLabel(SignupViewModel.AGE_LABEL));
        genderDropdownPanel.add(new JLabel(SignupViewModel.GENDER_LABEL));
        weightDropdownPanel.add(new JLabel(SignupViewModel.WEIGHT_LABEL));
        heightDropdownPanel.add(new JLabel(SignupViewModel.HEIGHT_LABEL));

        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Man", "Woman", "Prefer not to say"});

        Integer ageRange[] = new Integer[101];
        Integer weightRange[] = new Integer[250];
        Integer heighRange[] = new Integer[250];

        for (int i=0;i<=100;i++) ageRange[i] = i;
        for (int i=10;i<=200;i++) weightRange[i] = i;
        for (int i=10;i<=200;i++) heighRange[i] = i;

        JComboBox<Integer> ageComboBox = new JComboBox<>(ageRange);
        JComboBox<Integer> weightComboBox = new JComboBox<>(weightRange);
        JComboBox<Integer> heightComboBox = new JComboBox<>(heighRange);

        genderComboBox.setSelectedIndex(0);
        ageComboBox.setSelectedIndex(0);
        heightComboBox.setSelectedIndex(30);
        weightComboBox.setSelectedIndex(10);

        genderDropdownPanel.add(genderComboBox);
        ageDropdownPanel.add(ageComboBox);
        heightDropdownPanel.add(heightComboBox);
        weightDropdownPanel.add(weightComboBox);

        SignupState signupState = signupViewModel.getState();
        signupState.setAge(0);
        signupState.setGender("Man");
        signupState.setHeight(10);
        signupState.setWeight(10);
        signupViewModel.setState(signupState);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        LabelTextPanel nameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.NAME_LABEL), nameInputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        logIn = new JButton(SignupViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        buttons.add(signUp);


        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            SignupState currentState = signupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword(),
                                    currentState.getName(),
                                    currentState.getAge(),
                                    currentState.getGender(),
                                    currentState.getHeight(),
                                    currentState.getWeight()
                            );
                        }
                    }
                }
        );
        logIn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(logIn)) {
                            viewManagerModel.setActiveView(loginViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        nameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = nameInputField.getText() + e.getKeyChar();
                        currentState.setName(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        ageComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignupState currentState = signupViewModel.getState();
                int selectedValue = (int) ageComboBox.getSelectedItem();
                currentState.setAge(selectedValue);
                signupViewModel.setState(currentState);
            }
        });

        genderComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignupState currentState = signupViewModel.getState();
                String selectedValue = (String) genderComboBox.getSelectedItem();
                currentState.setGender(selectedValue);
                signupViewModel.setState(currentState);
            }
        });

        heightComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignupState currentState = signupViewModel.getState();
                int selectedValue = (int) heightComboBox.getSelectedItem();
                currentState.setHeight(selectedValue);
                signupViewModel.setState(currentState);
            }
        });

        weightComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignupState currentState = signupViewModel.getState();
                int selectedValue = (int) weightComboBox.getSelectedItem();
                currentState.setWeight(selectedValue);
                signupViewModel.setState(currentState);
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(nameInfo);
        this.add(ageDropdownPanel);
        this.add(genderDropdownPanel);
        this.add(heightDropdownPanel);
        this.add(weightDropdownPanel);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

