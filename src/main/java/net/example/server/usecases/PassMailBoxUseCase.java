package net.example.server.usecases;

import net.example.pop3proto.Pop3StateType;
import net.example.server.Pop3SessionContext;
import net.example.server.repositories.UserEntity;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class PassMailBoxUseCase {

    private final Pop3SessionContext sessionContext;

    public PassMailBoxUseCase(Pop3SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }


    public List<String> execute(String cryptPassword) {
        System.out.println("[" + sessionContext.getClientIP() + "] " + " execute PassMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();
        UserEntity user = sessionContext.getUser();
        if (user != null) {
            String userPassword = DigestUtils.md5Hex(user.getClearPassword());
            if (sessionContext.getSessionState() == Pop3StateType.WAITPASS) {
                if (userPassword.equals(cryptPassword)) {
                    result.add("+OK successful login");
                    sessionContext.setSessionStateAUTHORIZATION();
                    sessionContext.setAuthenticated(true);
                } else {
                    result.add("-ERR incorrect pass");
                }
            } else {
                System.out.println("[" + sessionContext.getClientIP() + "] " + " User has incorrect state. Actual: " + sessionContext.getSessionState() + " Expected WAITPASS ");
                result.add("-ERR incorrect state");
            }
        } else {
            result.add("-ERR user not write username");
        }
        return result;
    }
}
