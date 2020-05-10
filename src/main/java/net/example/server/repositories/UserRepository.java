package net.example.server.repositories;

import java.util.LinkedList;
import java.util.List;

public class UserRepository {
    private final List<UserEntity> users;

    public UserRepository() {
        users = new LinkedList<>();
        users.add(UserEntity.builder()
                .username("user1")
                .clearPassword("password1")
                .build());
        users.add(UserEntity.builder()
                .username("user2")
                .clearPassword("password2")
                .build());
    }

    public UserEntity getUserByUsername(String username) {
        UserEntity userEntity = null;
        for (UserEntity u : users) {
            if (u.getUsername().equals(username)) {
                userEntity = u;
            }
        }
        return userEntity;
    }
}
