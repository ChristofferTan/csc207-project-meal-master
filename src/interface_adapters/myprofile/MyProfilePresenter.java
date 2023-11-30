package interface_adapters.myprofile;

import interface_adapters.ViewManagerModel;
import interface_adapters.edit_profile.EditProfileState;
import interface_adapters.edit_profile.EditProfileViewModel;
import use_case.myprofile.MyProfileOutputBoundary;
import use_case.myprofile.MyProfileOutputData;

public class MyProfilePresenter implements MyProfileOutputBoundary{
    private final MyProfileViewModel myProfileViewModel;
    private final ViewManagerModel viewManagerModel;
    public MyProfilePresenter(MyProfileViewModel myProfileViewModel, ViewManagerModel viewManagerModel) {
        this.myProfileViewModel = myProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(MyProfileOutputData myProfileOutputData) {
        System.out.println("Output data " +  myProfileOutputData.getUsername());
        MyProfileState myProfileState = myProfileViewModel.getState();
        myProfileState.setUsername(myProfileOutputData.getUsername());
        myProfileState.setName(myProfileOutputData.getName());
        myProfileState.setAge(myProfileOutputData.getAge());
        myProfileState.setGender(myProfileOutputData.getGender());
        myProfileState.setWeight(myProfileOutputData.getWeight());
        myProfileState.setHeight(myProfileOutputData.getHeight());
        myProfileState.setCalories(myProfileOutputData.getRecommendedCalories());
        this.myProfileViewModel.setState(myProfileState);
        this.myProfileViewModel.firePropertyChanged();
    }
}
