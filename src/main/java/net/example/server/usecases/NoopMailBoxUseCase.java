package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;

import java.util.ArrayList;
import java.util.List;

public class NoopMailBoxUseCase {

    private final Pop3SessionContext sessionContext;

    public NoopMailBoxUseCase(Pop3SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public List<String> execute() {
        System.out.println("[" + sessionContext.getClientIP() + "] " + " execute NoopMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();
        result.add("+Ok");
        return result;
    }
}
