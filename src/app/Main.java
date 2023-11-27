package app;

import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.RecipeFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupViewModel;
import use_case.generate_recipe.GenerateRecipeDataAccessInterface;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    private static RecipeFactory recipeFactory = new RecipeFactory();
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.

        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.

        GenerateRecipeViewModel generateRecipeViewModel = new GenerateRecipeViewModel();
        GenerateRecipeDataAccessInterface generateRecipeDataAccessObject = new FileRecipeDataAccessObject(recipeFactory);
//            LoginViewModel loginViewModel = new LoginViewModel();
//            LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
//            SignupViewModel signupViewModel = new SignupViewModel();

//            FileUserDataAccessObject userDataAccessObject;
//            try {
//                userDataAccessObject = new FileUserDataAccessObject(new CommonUserFactory());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

//            SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
//            views.add(signupView, signupView.viewName);
//
//            LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
//            views.add(loginView, loginView.viewName);
//
//            LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
//            views.add(loggedInView, loggedInView.viewName);

        GenerateRecipeView generateRecipeView = GenerateRecipeUseCaseFactory.create(viewManagerModel, generateRecipeViewModel, generateRecipeDataAccessObject);
        views.add(generateRecipeView, generateRecipeView.viewName);

        viewManagerModel.setActiveView(generateRecipeView.viewName);
        viewManagerModel.firePropertyChanged();
//
        application.pack();
        application.setVisible(true);
    }
}