package entity;

public interface UserFactory {
    User create(String username, String password);
}
