package entity;

public interface UserFactory {
    User create(String username, String password, String name, int age, String gender, int height, int weight, Planner planner);
}
