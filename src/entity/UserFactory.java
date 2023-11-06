package entity;

public interface UserFactory {
    CommonUser create(String username, String password);
}
