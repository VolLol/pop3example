package net.example.server.usecase;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.usecases.RsetMailBoxUseCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RsetMailboxUseCaseTest {

    @Test
    public void correctAnswer() {
        String clientIP = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        mailBoxRepository.get(2).setDeleted(true);
        mailBoxRepository.get(3).setDeleted(true);
        mailBoxRepository.get(4).setDeleted(true);
        RsetMailBoxUseCase rsetMailBoxUseCase = new RsetMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = rsetMailBoxUseCase.execute();

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("+OK maildrop has 6 messages", answer.get(0));
    }

    @Test
    public void notIdentified() {
        String clientIp = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        sessionContext.setAuthenticated(false);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        RsetMailBoxUseCase retrMailBoxUseCase = new RsetMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = retrMailBoxUseCase.execute();

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR no authentication", answer.get(0));

    }

}
