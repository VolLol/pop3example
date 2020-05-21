package net.example.server.usecase;

import net.example.pop3proto.Pop3StateType;
import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.MailEntity;
import net.example.server.repositories.UserEntity;
import net.example.server.repositories.UserRepository;
import net.example.server.usecases.QuitMailBoxUsecase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class QuitMailBoxUseCaseTest {

    @Test
    public void correctQuit() {
        String clientIp = "clientIp";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        MailEntity mailEntity = mailBoxRepository.get(3);
        mailEntity.setDeleted(true);
        UserRepository userRepository = new UserRepository();
        UserEntity user = userRepository.getUserByUsername("user1");
        sessionContext.setUser(user);
        sessionContext.setSessionStateWAITPASS();
        sessionContext.setSessionStateAUTHORIZATION();
        QuitMailBoxUsecase quitMailBoxUsecase = new QuitMailBoxUsecase(sessionContext, mailBoxRepository);

        List<String> answer = quitMailBoxUsecase.execute();

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("+OK user1 POP3 server signing off", answer.get(0));
        Assert.assertEquals(Pop3StateType.NOAUTHORIZATION, sessionContext.getSessionState());
        Assert.assertFalse(sessionContext.isAuthenticated());
        Assert.assertFalse(mailEntity.isDeleted());
        Assert.assertFalse(sessionContext.isAuthenticated());
    }

    @Test
    public void incorrectQuit() {
        String clientIp = "clientIp";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        QuitMailBoxUsecase quitMailBoxUsecase = new QuitMailBoxUsecase(sessionContext, mailBoxRepository);
        List<String> answer = quitMailBoxUsecase.execute();

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR user not start work", answer.get(0));
        Assert.assertEquals(Pop3StateType.NOAUTHORIZATION, sessionContext.getSessionState());
    }
}
