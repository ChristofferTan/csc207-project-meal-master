package interface_adapters.myprofile;

import use_case.myprofile.MyProfileOutputBoundary;
import use_case.myprofile.MyProfileOutputData;

public class MyProfilePresenter implements MyProfileOutputBoundary{
    public MyProfilePresenter() {

    }

    @Override
    public void prepareSuccessView(MyProfileOutputData myProfileOutputData) {
        System.out.println("Username: " + myProfileOutputData.getUsername());
        System.out.println("Name: " + myProfileOutputData.getName());
        System.out.println("Age: " + myProfileOutputData.getAge());
        System.out.println("Height: " + myProfileOutputData.getHeight());
        System.out.println("Weight: " + myProfileOutputData.getWeight());
        System.out.println("Gender: " + myProfileOutputData.getGender());
    }
}
