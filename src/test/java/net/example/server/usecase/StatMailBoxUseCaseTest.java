package net.example.server.usecase;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.usecases.StatMailBoxUseCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StatMailBoxUseCaseTest {

    @Test
    public void correctStatus() {
        String clientIp = "clientIp";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        StatMailBoxUseCase statMailBoxUseCaseTest = new StatMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = statMailBoxUseCaseTest.execute();

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("+OK 6 2117", answer.get(0));
    }

    @Test
    public void correctStatusWithDeletedMessages() {
        String clientIp = "clientIp";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        mailBoxRepository.get(3).setDeleted(true);
        StatMailBoxUseCase statMailBoxUseCaseTest = new StatMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = statMailBoxUseCaseTest.execute();

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("+OK 5 1824", answer.get(0));
    }

    @Test
    public void notAuthenticated() {
        String clientIp = "clientIp";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        StatMailBoxUseCase statMailBoxUseCaseTest = new StatMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = statMailBoxUseCaseTest.execute();

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR user not authenticated", answer.get(0));
    }
}
