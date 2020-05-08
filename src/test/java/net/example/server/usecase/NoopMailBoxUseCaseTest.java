package net.example.server.usecase;

import net.example.server.Pop3SessionContext;
import net.example.server.usecases.NoopMailBoxUseCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NoopMailBoxUseCaseTest {

    @Test
    public void correctCommand() {
        String clientIP = "clientIp";
        Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
        NoopMailBoxUseCase noopMailBoxUseCase = new NoopMailBoxUseCase(sessionContext);
        List<String> answer = noopMailBoxUseCase.execute();
        String message = "[" + sessionContext.getClientIP() + "] " + " execute NoopMailBoxUseCase";


        Assert.assertEquals(1, answer.size());
        Assert.assertEquals("+Ok", answer.get(0));
        Assert.assertEquals("[clientIp]  execute NoopMailBoxUseCase", message);
    }
}
