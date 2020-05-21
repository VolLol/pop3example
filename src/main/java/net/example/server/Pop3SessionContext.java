package net.example.server;

import lombok.Getter;
import lombok.Setter;
import net.example.pop3proto.Pop3StateType;
import net.example.server.exceptions.IncorrectState;
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

    @Getter
    private Pop3StateType sessionState;

    public Pop3SessionContext(String clientIP) {
        this.clientIP = clientIP;
        this.isAuthenticated = false;
        this.sessionState = Pop3StateType.NOAUTHORIZATION;
    }

    public void setSessionStateWAITPASS() {
        if (sessionState.equals(Pop3StateType.NOAUTHORIZATION)) {
            sessionState = Pop3StateType.WAITPASS;
        } else {
            throw new IncorrectState("Incorrect state, can't change session state to WAITPASS");
        }
    }

    public void setSessionStateAUTHORIZATION() {
        if (sessionState.equals(Pop3StateType.WAITPASS)) {
            sessionState = Pop3StateType.AUTHORIZATION;
        } else {
            throw new IncorrectState("Incorrect state, can't change session state to AUTHORIZATION");
        }
    }

    public void setSessionStateNOAUTHORIZATION() {
        if (sessionState.equals(Pop3StateType.AUTHORIZATION)) {
            sessionState = Pop3StateType.NOAUTHORIZATION;
        } else {
            throw new IncorrectState("Incorrect state, can't change session state to AUTHORIZATION");
        }
    }
}
