package entity;

public class CommonUserFactory implements UserFactory{
    @Override
    public User create(String username, String password, String name, int age, String gender, int height, int weight) {
        return new CommonUser(username, password, name, age, gender, height, weight);
    }
}
