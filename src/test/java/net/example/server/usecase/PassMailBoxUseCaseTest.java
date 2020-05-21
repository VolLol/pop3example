package net.example.server.usecase;

import net.example.pop3proto.Pop3StateType;
import net.example.server.Pop3SessionContext;
import net.example.server.repositories.UserEntity;
import net.example.server.repositories.UserRepository;
import net.example.server.usecases.PassMailBoxUseCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PassMailBoxUseCaseTest {

    @Test
    public void correctPass() {
        String clientIp = "clientIp";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        UserRepository userRepository = new UserRepository();
        UserEntity userEntity = userRepository.getUserByUsername("user1");
        sessionContext.setUser(userEntity);
        sessionContext.setSessionStateWAITPASS();
        PassMailBoxUseCase passMailBoxUseCase = new PassMailBoxUseCase(sessionContext);

        List<String> answer = passMailBoxUseCase.execute("7c6a180b36896a0a8c02787eeafb0e4c");

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("+OK successful login", answer.get(0));
        Assert.assertEquals(Pop3StateType.AUTHORIZATION, sessionContext.getSessionState());
    }

    @Test
    public void incorrectPass() {
        String clientIp = "clientIp";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        UserRepository userRepository = new UserRepository();
        UserEntity userEntity = userRepository.getUserByUsername("user1");
        sessionContext.setUser(userEntity);
        sessionContext.setSessionStateWAITPASS();
        PassMailBoxUseCase passMailBoxUseCase = new PassMailBoxUseCase(sessionContext);

        List<String> answer = passMailBoxUseCase.execute("not correct pass");

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR incorrect pass", answer.get(0));
        Assert.assertEquals(Pop3StateType.WAITPASS, sessionContext.getSessionState());
    }

    @Test
    public void incorrectState() {
        String clientIp = "clientIp";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        UserRepository userRepository = new UserRepository();
        UserEntity userEntity = userRepository.getUserByUsername("user1");
        sessionContext.setUser(userEntity);
        PassMailBoxUseCase passMailBoxUseCase = new PassMailBoxUseCase(sessionContext);

        List<String> answer = passMailBoxUseCase.execute("7c6a180b36896a0a8c02787eeafb0e4c");

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR incorrect state", answer.get(0));
        Assert.assertEquals(Pop3StateType.NOAUTHORIZATION, sessionContext.getSessionState());
    }

    @Test
    public void withoutUsername() {
        String clientIp = "clientIp";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        sessionContext.setSessionStateWAITPASS();
        PassMailBoxUseCase passMailBoxUseCase = new PassMailBoxUseCase(sessionContext);

        List<String> answer = passMailBoxUseCase.execute("7c6a180b36896a0a8c02787eeafb0e4c");

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR user not write username", answer.get(0));
        Assert.assertEquals(Pop3StateType.WAITPASS, sessionContext.getSessionState());
    }
}
