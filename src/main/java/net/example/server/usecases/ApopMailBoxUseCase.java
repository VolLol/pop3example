package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.UserEntity;
import net.example.server.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class ApopMailBoxUseCase {

    private final UserRepository userRepository;
    private final Pop3SessionContext sessionContext;


    public ApopMailBoxUseCase(Pop3SessionContext sessionContext, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.sessionContext = sessionContext;
    }

    public List<String> execute(String username, String cryptPassword) {
        System.out.println("[" + sessionContext.getClientIP() + "] " + " execute ApopMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();
        UserEntity user = userRepository.getUserByUsername(username);
        if (user != null) {
            MailBoxRepository mailBoxRepository = new MailBoxRepository();
            int countOfMessage = mailBoxRepository.list().size();
            String passwordFromdb = DigestUtils.md5Hex(user.getClearPassword());
            if (passwordFromdb.equals(cryptPassword)) {
                sessionContext.setAuthenticated(true);
                result.add("+OK maildrop has " + countOfMessage + " message");
            } else {
                result.add("-ERR password supplied for " + username + " is incorrect");
            }
        } else {
            result.add("-ERR User with name " + username + " not exist");
        }
        return result;
    }
}
