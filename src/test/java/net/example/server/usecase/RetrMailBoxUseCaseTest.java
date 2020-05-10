package net.example.server.usecase;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.MailEntity;
import net.example.server.usecases.RetrMailBoxUseCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RetrMailBoxUseCaseTest {

    @Test
    public void correctRequest() {
        int mailIndex = 3;
        String clientIP = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        RetrMailBoxUseCase retrMailBoxUseCase = new RetrMailBoxUseCase(sessionContext, mailBoxRepository);
        MailEntity mailEntity = mailBoxRepository.get(3);

        List<String> answer = retrMailBoxUseCase.execute(mailIndex);

        Assert.assertEquals(5, answer.size());
        Assert.assertEquals("+OK message follows", answer.get(0));
        Assert.assertEquals(mailEntity.getSubject(), answer.get(1));
        Assert.assertEquals(mailEntity.getFrom(), answer.get(2));
        Assert.assertEquals(mailEntity.getTo(), answer.get(3));
        Assert.assertEquals(mailEntity.getPayload(), answer.get(4));
    }


    @Test
    public void incorrectMailIndex1() {
        int mailIndex = 20;
        String clientIp = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        RetrMailBoxUseCase retrMailBoxUseCase = new RetrMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = retrMailBoxUseCase.execute(mailIndex);

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR no such message", answer.get(0));
    }

    @Test
    public void incorrectMailIndex2() {
        int mailIndex = -5;
        String clientIp = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        RetrMailBoxUseCase retrMailBoxUseCase = new RetrMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = retrMailBoxUseCase.execute(mailIndex);

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR no such message", answer.get(0));
    }

    @Test
    public void incorrectMailIndex3() {
        int mailIndex = 0;
        String clientIp = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        RetrMailBoxUseCase retrMailBoxUseCase = new RetrMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = retrMailBoxUseCase.execute(mailIndex);

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR no such message", answer.get(0));
    }

    @Test
    public void notIdentified(){
        int mailIndex = 0;
        String clientIp = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIp);
        sessionContext.setAuthenticated(false);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        RetrMailBoxUseCase retrMailBoxUseCase = new RetrMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = retrMailBoxUseCase.execute(mailIndex);

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR no authentication", answer.get(0));

    }

}
