package net.example.server;

import lombok.Getter;
import lombok.Setter;
import net.example.server.repositories.UserEntity;

public class Pop3SessionContext {
    @Getter
    private final String clientIP;

    @Getter
    @Setter
    private UserEntity user;

    @Getter
    @Setter
    private boolean isAuthenticated;

    public Pop3SessionContext(String clientIP) {

        this.clientIP = clientIP;
        this.isAuthenticated = false;
    }
}
