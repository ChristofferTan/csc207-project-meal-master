package interface_adapters.my_profile;

import interface_adapters.ViewManagerModel;
import use_case.my_profile.MyProfileOutputBoundary;
import use_case.my_profile.MyProfileOutputData;

public class MyProfilePresenter implements MyProfileOutputBoundary{
    private final MyProfileViewModel myProfileViewModel;
    private final ViewManagerModel viewManagerModel;
    public MyProfilePresenter(MyProfileViewModel myProfileViewModel, ViewManagerModel viewManagerModel) {
        this.myProfileViewModel = myProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(MyProfileOutputData myProfileOutputData) {
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
