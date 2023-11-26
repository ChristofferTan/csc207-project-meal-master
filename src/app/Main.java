package app;

import api.edamam.GenerateRecipeAPICaller;
import data_access.FilePlannerDataAccessObject;
import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapters.ViewManagerModel;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import interface_adapters.grocery_list.GroceryListController;
import interface_adapters.grocery_list.GroceryListViewModel;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.save_recipe.SaveRecipeController;
import interface_adapters.save_recipe.SaveRecipeViewModel;
import interface_adapters.signup.SignupViewModel;
import use_case.generate_recipe.GenerateRecipeInputData;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.DayOfWeek;

public class Main {
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

            GroceryListViewModel groceryListViewModel = new GroceryListViewModel();

            FilePlannerDataAccessObject fpdao = new FilePlannerDataAccessObject(new PlannerFactory(), new FileRecipeDataAccessObject(new RecipeFactory()));
            SaveRecipeView saveRecipeView = SaveRecipeUseCaseFactory.create(
                    new ViewManagerModel(),
                    new SaveRecipeViewModel(),
                    fpdao
            );
            SaveRecipeController saveRecipeController = saveRecipeView.getSaveRecipeController();
            GenerateRecipeInputData generateRecipeInputData = new GenerateRecipeInputData(
                    "chicken",
                    new String[]{"balanced"},
                    new String[]{"alcohol-free"},
                    new String[]{"Asian"},
                    new String[]{"Lunch"},
                    "100-1000",
                    "0-100"
            );
            Recipe expectedRecipe = GenerateRecipeAPICaller.call(generateRecipeInputData).getRecipe();
            saveRecipeController.execute("budi", DayOfWeek.MONDAY, MealType.LUNCH, expectedRecipe);
            Recipe actualRecipe = fpdao.getPlanner("budi").getRecipesByDay(DayOfWeek.MONDAY).get(MealType.LUNCH);

//            GenerateRecipeViewModel generateRecipeViewModel = new GenerateRecipeViewModel();
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

//            GenerateRecipeView generateRecipeView = GenerateRecipeUseCaseFactory.create(viewManagerModel, generateRecipeViewModel);
//            views.add(generateRecipeView, generateRecipeView.viewName);

            GroceryListView groceryListView = GroceryListUseCaseFactory.create(viewManagerModel, groceryListViewModel, fpdao);
            views.add(groceryListView, groceryListView.viewName);

            GroceryListController groceryListController = groceryListView.getGroceryListController();
            groceryListController.execute("budi");

//            viewManagerModel.setActiveView(generateRecipeView.viewName);
//            viewManagerModel.firePropertyChanged();

            viewManagerModel.setActiveView(groceryListView.viewName);
            viewManagerModel.firePropertyChanged();

            application.pack();
            application.setVisible(true);
    }
}