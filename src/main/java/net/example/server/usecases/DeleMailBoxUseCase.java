package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;

import java.util.ArrayList;
import java.util.List;

public class DeleMailBoxUseCase {

    private final MailBoxRepository mailBoxRepository;
    private final Pop3SessionContext sessionContext;

    public DeleMailBoxUseCase(Pop3SessionContext sessionContext, MailBoxRepository mailBoxRepository) {
        this.mailBoxRepository = mailBoxRepository;
        this.sessionContext = sessionContext;
    }

    public List<String> execute(int mailIndex) {
        System.out.println("[" + sessionContext.getClientIP() + "] " + " execute DeleMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();
        return result;
    }
}
