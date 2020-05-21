package net.example.server.usecase;

import net.example.pop3proto.Pop3StateType;
import net.example.server.Pop3SessionContext;
import net.example.server.repositories.UserRepository;
import net.example.server.usecases.UserMailBoxUseCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserMailBoxUseCaseTest {

    @Test
    public void correctUser() {
        String clientIp = "clientIp";
        UserRepository userRepository = new UserRepository();
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        UserMailBoxUseCase userMailBoxUseCase = new UserMailBoxUseCase(sessionContext, userRepository);

        List<String> answer = userMailBoxUseCase.execute("user1");

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("+Ok user1 name is a valid mailbox", answer.get(0));
        Assert.assertEquals(Pop3StateType.WAITPASS, sessionContext.getSessionState());
    }

    @Test
    public void notcorrectUser() {
        String clientIp = "clientIp";
        UserRepository userRepository = new UserRepository();
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        UserMailBoxUseCase userMailBoxUseCase = new UserMailBoxUseCase(sessionContext, userRepository);

        List<String> answer = userMailBoxUseCase.execute("notcorrectuser");

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR not exist username notcorrectuser", answer.get(0));
        Assert.assertEquals(Pop3StateType.NOAUTHORIZATION, sessionContext.getSessionState());
    }

    @Test
    public void incorrectState() {
        String clientIp = "clientIp";
        UserRepository userRepository = new UserRepository();
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        sessionContext.setSessionStateWAITPASS();
        sessionContext.setSessionStateAUTHORIZATION();
        sessionContext.setAuthenticated(true);
        UserMailBoxUseCase userMailBoxUseCase = new UserMailBoxUseCase(sessionContext, userRepository);

        List<String> answer = userMailBoxUseCase.execute("user1");

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR not correct state", answer.get(0));
        Assert.assertEquals(Pop3StateType.AUTHORIZATION, sessionContext.getSessionState());
    }
}
