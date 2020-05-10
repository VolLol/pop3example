package net.example.server.usecase;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.usecases.ListMailBoxUseCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ListMailBoxUseCaseTest {

    @Test
    public void correctAnswer1() {
        String clientIP = "clientIP";
        Integer limit = 2;
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        ListMailBoxUseCase listMailBoxUseCase = new ListMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = listMailBoxUseCase.execute(limit);

        Assert.assertEquals(3, answer.size());
        Assert.assertEquals("+OK 6 2117", answer.get(0));
        Assert.assertEquals("1 293", answer.get(1));
        Assert.assertEquals("2 293", answer.get(2));
    }


    @Test
    public void correctAnswer2() {
        String clientIP = "clientIP";
        Integer limit = 0;
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        ListMailBoxUseCase listMailBoxUseCase = new ListMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = listMailBoxUseCase.execute(limit);

        Assert.assertEquals(7, answer.size());
        Assert.assertEquals("+OK 6 2117", answer.get(0));
        Assert.assertEquals("1 293", answer.get(1));
        Assert.assertEquals("2 293", answer.get(2));
        Assert.assertEquals("3 293", answer.get(3));
        Assert.assertEquals("4 293", answer.get(4));
        Assert.assertEquals("5 293", answer.get(5));
        Assert.assertEquals("6 652", answer.get(6));
    }

    @Test
    public void notIdentified() {
        String clientIP = "clientIP";
        Integer limit = 4;
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(false);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        ListMailBoxUseCase listMailBoxUseCase = new ListMailBoxUseCase(sessionContext, mailBoxRepository);

        List<String> answer = listMailBoxUseCase.execute(limit);

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR no authentication", answer.get(0));

    }
}