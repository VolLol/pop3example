package net.example.server.usecases;

import net.example.pop3proto.Pop3StateType;
import net.example.server.Pop3SessionContext;
import net.example.server.repositories.UserEntity;
import net.example.server.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserMailBoxUseCase {

    private final Pop3SessionContext sessionContext;
    private final UserRepository userRepository;

    public UserMailBoxUseCase(Pop3SessionContext sessionContext, UserRepository userRepository) {
        this.sessionContext = sessionContext;
        this.userRepository = userRepository;
    }

    public List<String> execute(String username) {
        System.out.println("[" + sessionContext.getClientIP() + "] " + " execute UserMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();

        UserEntity userEntity = userRepository.getUserByUsername(username);
        if (userEntity != null) {
            if (sessionContext.getSessionState() == Pop3StateType.NOAUTHORIZATION) {
                result.add("+Ok " + username + " name is a valid mailbox");
                sessionContext.setSessionStateWAITPASS();
                sessionContext.setUser(userEntity);
            } else {
                System.out.println("[" + sessionContext.getClientIP() + "] " + " User has incorrect state. Actual: " + sessionContext.getSessionState() + " Expected NOAUTHORIZATION ");
                result.add("-ERR not correct state");
            }
        } else {
            result.add("-ERR not exist username " + username);
        }
        return result;


    }
}
