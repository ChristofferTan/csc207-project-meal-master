package app;

import data_access.FilePlannerDataAccessObject;
import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapters.ViewManagerModel;
import interface_adapters.add_favorite_recipe.AddFavoriteRecipeViewModel;
import interface_adapters.after_generated_recipe.AfterGeneratedRecipeViewModel;
import interface_adapters.edit_profile.EditProfileViewModel;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import interface_adapters.grocery_list.GroceryListController;
import interface_adapters.grocery_list.GroceryListViewModel;
import interface_adapters.logged_in.LoggedInViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeController;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeViewModel;
import interface_adapters.my_planner.MyPlannerController;
import interface_adapters.my_planner.MyPlannerViewModel;
import interface_adapters.my_profile.MyProfileController;
import interface_adapters.my_profile.MyProfileViewModel;
import interface_adapters.save_recipe.SaveRecipeViewModel;
import interface_adapters.signup.SignupViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
            // Build the main program window, the main panel containing the
            // various cards, and the layout, and stitch them together.

            // The main application window.

            JFrame application = new JFrame("Meal Master");
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

//            GroceryListViewModel groceryListViewModel = new GroceryListViewModel();

            FilePlannerDataAccessObject fpdao = new FilePlannerDataAccessObject(new PlannerFactory(), new FileRecipeDataAccessObject(new RecipeFactory()));
            SaveRecipeView saveRecipeView = SaveRecipeUseCaseFactory.create(
                    new ViewManagerModel(),
                    new SaveRecipeViewModel(),
                    fpdao
            );


            FileRecipeDataAccessObject frdao = new FileRecipeDataAccessObject(new RecipeFactory());
            GroceryListViewModel groceryListViewModel = new GroceryListViewModel();
            GenerateRecipeViewModel generateRecipeViewModel = new GenerateRecipeViewModel();
            AfterGeneratedRecipeViewModel afterGeneratedRecipeViewModel = new AfterGeneratedRecipeViewModel();
            LoginViewModel loginViewModel = new LoginViewModel();
            LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
            SignupViewModel signupViewModel = new SignupViewModel();
            SaveRecipeViewModel saveRecipeViewModel = new SaveRecipeViewModel();
            AddFavoriteRecipeViewModel addFavoriteRecipeViewModel = new AddFavoriteRecipeViewModel();
            MyProfileViewModel myProfileViewModel = new MyProfileViewModel();
            EditProfileViewModel editProfileViewModel = new EditProfileViewModel();
            MyPlannerViewModel myPlannerViewModel = new MyPlannerViewModel();
            MyFavoriteRecipeViewModel myFavoriteRecipeViewModel = new MyFavoriteRecipeViewModel();


            FileUserDataAccessObject userDataAccessObject;
            try {
                userDataAccessObject = new FileUserDataAccessObject(new CommonUserFactory());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
            views.add(signupView, signupView.viewName);

            LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, signupViewModel, userDataAccessObject);
            views.add(loginView, loginView.viewName);

            GenerateRecipeView generateRecipeView = GenerateRecipeUseCaseFactory.create(viewManagerModel, generateRecipeViewModel, afterGeneratedRecipeViewModel, frdao);
            views.add(generateRecipeView, generateRecipeView.viewName);

            AfterGeneratedRecipeView afterGeneratedRecipeView = AfterGeneratedRecipeFactory.create(viewManagerModel, afterGeneratedRecipeViewModel, generateRecipeViewModel, saveRecipeViewModel, addFavoriteRecipeViewModel, fpdao, userDataAccessObject, frdao);
            views.add(afterGeneratedRecipeView, afterGeneratedRecipeView.viewName);

            GroceryListView groceryListView = GroceryListUseCaseFactory.create(viewManagerModel, groceryListViewModel, fpdao);
            views.add(groceryListView, groceryListView.viewName);
            GroceryListController groceryListController = groceryListView.getGroceryListController();

            MyFavoriteRecipeView myFavoriteRecipeView = MyFavoriteRecipeFactory.create(viewManagerModel, myFavoriteRecipeViewModel, userDataAccessObject, frdao, saveRecipeViewModel, fpdao);
            views.add(myFavoriteRecipeView, myFavoriteRecipeView.viewName);
            MyFavoriteRecipeController myFavoriteRecipeController = myFavoriteRecipeView.getMyFavoriteRecipeController();

            MyProfileView myProfileView = MyProfileFactory.create(viewManagerModel, myProfileViewModel, editProfileViewModel, userDataAccessObject);
            views.add(myProfileView, myProfileView.viewName);
            MyProfileController myProfileController = myProfileView.getMyProfileController();

            MyPlannerView myPlannerView = MyPlannerUseCaseFactory.create(viewManagerModel, myPlannerViewModel, fpdao);
            views.add(myPlannerView, myPlannerView.viewName);
            MyPlannerController myPlannerController = myPlannerView.getMyPlannerController();

            LoggedInView loggedInView = new LoggedInView(loggedInViewModel, viewManagerModel, groceryListController, myProfileController, myPlannerController, generateRecipeViewModel, myFavoriteRecipeController, myFavoriteRecipeViewModel);
            views.add(loggedInView, loggedInView.viewName);

            EditProfileView editProfileView = EditProfileFactory.create(viewManagerModel, editProfileViewModel,userDataAccessObject, myProfileViewModel);
            views.add(editProfileView, editProfileView.viewName);

            viewManagerModel.setActiveView(signupView.viewName);
            viewManagerModel.firePropertyChanged();

            application.pack();
            toggleFullscreen(application);
            application.setVisible(true);
    }

    private static void toggleFullscreen(JFrame application) {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            int screenWidth = gd.getDisplayMode().getWidth();
            int screenHeight = gd.getDisplayMode().getHeight();
            application.setSize(screenWidth, screenHeight);
    }
}