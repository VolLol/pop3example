package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;

import java.util.ArrayList;
import java.util.List;

public class TopMailBoxUseCase {

    private final MailBoxRepository mailBoxRepository;
    private final Pop3SessionContext sessionContext;


    public TopMailBoxUseCase(Pop3SessionContext sessionContext, MailBoxRepository mailBoxRepository) {
        this.mailBoxRepository = mailBoxRepository;
        this.sessionContext = sessionContext;
    }

    public List<String> execute(int mailIndex, int countLines) {
        System.out.println("[" + sessionContext.getClientIP() + "] " + " execute TopMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();
        return result;
    }
}
