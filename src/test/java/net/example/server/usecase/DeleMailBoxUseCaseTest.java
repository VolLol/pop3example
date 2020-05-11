package net.example.server.usecase;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.usecases.DeleMailBoxUseCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DeleMailBoxUseCaseTest {

    @Test
    public void correctAnswer() {
        String clientIP = "clientIP";
        int mailIndex = 1;
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        DeleMailBoxUseCase deleMailBoxUseCase = new DeleMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = deleMailBoxUseCase.execute(mailIndex);

        Assert.assertTrue(sessionContext.isAuthenticated());
        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("+Ok message 1 is deleted", answer.get(0));
    }

    @Test
    public void incorrectIndex2() {
        String clientIP = "clientIP";
        int mailIndex = 0;
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        DeleMailBoxUseCase deleMailBoxUseCase = new DeleMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = deleMailBoxUseCase.execute(mailIndex);

        Assert.assertTrue(sessionContext.isAuthenticated());
        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR not correct index", answer.get(0));
    }
    @Test
    public void alreadyDeleted() {
        String clientIP = "clientIP";
        int mailIndex = 2;
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        mailBoxRepository.list().get(mailIndex - 1).setDeleted(true);
        DeleMailBoxUseCase deleMailBoxUseCase = new DeleMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = deleMailBoxUseCase.execute(mailIndex);

        Assert.assertTrue(sessionContext.isAuthenticated());
        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR message 2 already deleted", answer.get(0));
    }


    @Test
    public void incorrectIndex() {
        String clientIP = "clientIP";
        int mailIndex = 10;
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        DeleMailBoxUseCase deleMailBoxUseCase = new DeleMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = deleMailBoxUseCase.execute(mailIndex);

        Assert.assertTrue(sessionContext.isAuthenticated());
        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR not correct index", answer.get(0));
    }


    @Test
    public void notIdentified() {
        String clientIP = "clientIP";
        int mailIndex = 3;
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(false);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        DeleMailBoxUseCase deleMailBoxUseCase = new DeleMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = deleMailBoxUseCase.execute(mailIndex);

        Assert.assertFalse(sessionContext.isAuthenticated());
        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR no authentication", answer.get(0));
    }
}
