package net.example.server.repositories;

import lombok.Builder;
import lombok.Getter;

@Builder
public class UserEntity {
    @Getter
    private String username;
    @Getter
    private String clearPassword;
}
