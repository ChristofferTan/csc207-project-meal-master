package entity;

import java.util.ArrayList;

public interface User {
    String getUsername();
    String getPassword();
    void setName(String name);
    void setAge(int age);
    void setGender(String gender);
    void setWeight(double weight);
    void setHeight(double height);
    void setActivityLevel(String activityLevel);
    void setDietLabels(ArrayList<String> dietLabels);
    void setHealthLabels(ArrayList<String> healthLabels);
}
