package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        System.out.print("What is the keyword? ");
//        String q = in.nextLine();
//
//        System.out.print("One of your diet preference? ");
//        String[] diet = {in.nextLine()};
//
//        System.out.print("One of your health preference? ");
//        String[] health = {in.nextLine()};
//
//        System.out.print("One of your cuisine type preference? ");
//        String[] cuisineType = {in.nextLine()};
//
//        System.out.print("One of your meal type preference? ");
//        String[] mealType = {in.nextLine()};
//
//        System.out.print("Your minimum calories (per serving) needed? ");
//        String minCalories = in.nextLine();
//
//        System.out.print("Your maximum calories (per serving) needed? ");
//        String maxCalories = in.nextLine();
//
//        System.out.print("Max preparation time (in minutes)? ");
//        String maxPreparationTime = in.nextLine();
//
//
//        GenerateRecipeController generateRecipeController = GenerateRecipeFactory.createGenerateUseCase();
//        generateRecipeController.execute(q, diet, health, cuisineType, mealType, minCalories, maxCalories, maxPreparationTime);

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
//            LoginViewModel loginViewModel = new LoginViewModel();
//            LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
//            SignupViewModel signupViewModel = new SignupViewModel();

//            FileUserDataAccessObject userDataAccessObject;
//            try {
//                userDataAccessObject = new FileUserDataAccessObject("users.csv", new CommonUserFactory());
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

            GenerateRecipeView generateRecipeView = GenerateRecipeFactory.create(viewManagerModel, generateRecipeViewModel);
            views.add(generateRecipeView, generateRecipeView.viewName);

            viewManagerModel.setActiveView(generateRecipeView.viewName);
            viewManagerModel.firePropertyChanged();

            application.pack();
            application.setVisible(true);

    }
}