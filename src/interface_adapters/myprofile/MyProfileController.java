package interface_adapters.myprofile;

import use_case.myprofile.MyProfileInputBoundary;
import use_case.myprofile.MyProfileInputData;

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
