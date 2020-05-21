package net.example.server.usecase;

import net.example.pop3proto.Pop3StateType;
import net.example.server.Pop3SessionContext;
import net.example.server.repositories.UserRepository;
import net.example.server.usecases.ApopMailBoxUseCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ApopMailBoxUseCaseTest {

    @Test
    public void correctAnswer() {
        String clienIP = "clientIP";
        String username = "user1";
        String cryptPassword = "7c6a180b36896a0a8c02787eeafb0e4c";
        UserRepository userRepository = new UserRepository();
        Pop3SessionContext sessionContext = new Pop3SessionContext(clienIP);
        ApopMailBoxUseCase apopMailBoxUseCase = new ApopMailBoxUseCase(sessionContext, userRepository);
        List<String> answer = apopMailBoxUseCase.execute(username, cryptPassword);

        Assert.assertTrue(sessionContext.isAuthenticated());
        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("+OK maildrop has 6 message", answer.get(0));
        Assert.assertEquals(Pop3StateType.AUTHORIZATION, sessionContext.getSessionState());
    }

    @Test
    public void incorrectPasssword() {
        String clienIP = "clientIP";
        String username = "user1";
        String cryptPassword = "password";
        UserRepository userRepository = new UserRepository();
        Pop3SessionContext sessionContext = new Pop3SessionContext(clienIP);
        ApopMailBoxUseCase apopMailBoxUseCase = new ApopMailBoxUseCase(sessionContext, userRepository);
        List<String> answer = apopMailBoxUseCase.execute(username, cryptPassword);

        Assert.assertFalse(sessionContext.isAuthenticated());
        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR password supplied for user1 is incorrect", answer.get(0));
        Assert.assertEquals(Pop3StateType.NOAUTHORIZATION, sessionContext.getSessionState());

    }

    @Test
    public void incorrectUsername() {
        String clienIP = "clientIP";
        String username = "lolkek";
        String cryptPassword = "password";
        UserRepository userRepository = new UserRepository();
        Pop3SessionContext sessionContext = new Pop3SessionContext(clienIP);
        ApopMailBoxUseCase apopMailBoxUseCase = new ApopMailBoxUseCase(sessionContext, userRepository);
        List<String> answer = apopMailBoxUseCase.execute(username, cryptPassword);

        Assert.assertFalse(sessionContext.isAuthenticated());
        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR User with name lolkek not exist", answer.get(0));
        Assert.assertEquals(Pop3StateType.NOAUTHORIZATION, sessionContext.getSessionState());

    }

    @Test
    public void notcorrectState() {
        String clienIP = "clientIP";
        String username = "user1";
        String cryptPassword = "7c6a180b36896a0a8c02787eeafb0e4c";
        UserRepository userRepository = new UserRepository();
        Pop3SessionContext sessionContext = new Pop3SessionContext(clienIP);
        sessionContext.setSessionStateWAITPASS();
        sessionContext.setSessionStateAUTHORIZATION();
        sessionContext.setAuthenticated(true);
        ApopMailBoxUseCase apopMailBoxUseCase = new ApopMailBoxUseCase(sessionContext, userRepository);
        List<String> answer = apopMailBoxUseCase.execute(username, cryptPassword);

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR incorrect state", answer.get(0));
    }
}
