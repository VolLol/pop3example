package net.example.server.usecase;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.usecases.TopMailBoxUseCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TopMailBoxUseCaseTest {

    @Test
    public void correctAnswer1() {
        int mailIndex = 1;
        int countlines = 1;
        String clientIP = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        TopMailBoxUseCase topMailBoxUseCase = new TopMailBoxUseCase(sessionContext, mailBoxRepository);
        List<String> answer = topMailBoxUseCase.execute(mailIndex, countlines);

        Assert.assertEquals(5, answer.size());
        Assert.assertEquals("+OK top of message follows", answer.get(0));
        Assert.assertEquals("Sample message 1", answer.get(1));
        Assert.assertEquals("Author <author@example.com>", answer.get(2));
        Assert.assertEquals("Recipient <recipient@example.com>", answer.get(3));
        Assert.assertEquals("Notice how each subproject is prefixed in the output," +
                " so that you know which task from which project is being executed.", answer.get(4));
    }

    @Test
    public void correctAnswer2() {
        int mailIndex = 1;
        int countLines = 10;
        String clientIP = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        TopMailBoxUseCase topMailBoxUseCase = new TopMailBoxUseCase(sessionContext, mailBoxRepository);
        List<String> answer = topMailBoxUseCase.execute(mailIndex, countLines);

        Assert.assertEquals(6, answer.size());
        Assert.assertEquals("+OK top of message follows", answer.get(0));
        Assert.assertEquals("Sample message 1", answer.get(1));
        Assert.assertEquals("Author <author@example.com>", answer.get(2));
        Assert.assertEquals("Recipient <recipient@example.com>", answer.get(3));
        Assert.assertEquals("Notice how each subproject is prefixed in the output, so that you know which task from which project is being executed.", answer.get(4));
        Assert.assertEquals("Also note that Gradle does not process all tasks from one subproject before moving onto another.", answer.get(5));
    }

    @Test
    public void correctAnswerWithCountLinesZero() {
        int mailIndex = 1;
        int countlines = 0;
        String clientIP = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        TopMailBoxUseCase topMailBoxUseCase = new TopMailBoxUseCase(sessionContext, mailBoxRepository);
        List<String> answer = topMailBoxUseCase.execute(mailIndex, countlines);

        Assert.assertEquals(4, answer.size());
        Assert.assertEquals("+OK top of message follows", answer.get(0));
        Assert.assertEquals("Sample message 1", answer.get(1));
        Assert.assertEquals("Author <author@example.com>", answer.get(2));
        Assert.assertEquals("Recipient <recipient@example.com>", answer.get(3));
    }
    @Test
    public void incorrectAnswer1() {
        int mailIndex = -3;
        int countLines = 13;
        String clientIP = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        TopMailBoxUseCase topMailBoxUseCase = new TopMailBoxUseCase(sessionContext, mailBoxRepository);
        List<String> answer = topMailBoxUseCase.execute(mailIndex, countLines);

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR no such message", answer.get(0));
    }

    @Test
    public void incorrectAnswer2() {
        int mailIndex = 15;
        int countLines = 13;
        String clientIP = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(true);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        TopMailBoxUseCase topMailBoxUseCase = new TopMailBoxUseCase(sessionContext, mailBoxRepository);
        List<String> answer = topMailBoxUseCase.execute(mailIndex, countLines);

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR no such message", answer.get(0));
    }

    @Test
    public void notAutentificated() {
        int mailIndex = 3;
        int countLines = 3;
        String clientIP = "clientIP";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        sessionContext.setAuthenticated(false);
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        TopMailBoxUseCase topMailBoxUseCase = new TopMailBoxUseCase(sessionContext, mailBoxRepository);
        List<String> answer = topMailBoxUseCase.execute(mailIndex, countLines);

        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("-ERR no authentication", answer.get(0));

    }
}