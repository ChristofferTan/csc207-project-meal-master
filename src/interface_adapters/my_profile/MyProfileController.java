package interface_adapters.my_profile;

import use_case.my_profile.MyProfileInputBoundary;
import use_case.my_profile.MyProfileInputData;

public class MyProfileController {
    final MyProfileInputBoundary myProfileInteractor;


    public MyProfileController(MyProfileInputBoundary myProfileInteractor) {
        this.myProfileInteractor = myProfileInteractor;
    }

    public void execute(String username) {
        MyProfileInputData myProfileInputData = new MyProfileInputData(username);
        myProfileInteractor.execute(myProfileInputData);
    }
}
