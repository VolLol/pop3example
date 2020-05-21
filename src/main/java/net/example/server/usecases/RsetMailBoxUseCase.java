package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.MailEntity;

import java.util.ArrayList;
import java.util.List;


public class RsetMailBoxUseCase {

    private final Pop3SessionContext sessionContext;
    private final MailBoxRepository mailBoxRepository;

    public RsetMailBoxUseCase(Pop3SessionContext sessionContext, MailBoxRepository mailBoxRepository) {
        this.sessionContext = sessionContext;
        this.mailBoxRepository = mailBoxRepository;
    }


    public List<String> execute() {
        System.out.println("[" + sessionContext.getClientIP() + "] " + " execute RsetMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();
        if (sessionContext.isAuthenticated()) {
            List<MailEntity> listOfAllMails = mailBoxRepository.list();
            for (MailEntity m : listOfAllMails) {
                if (m.isDeleted()) {
                    m.setDeleted(false);
                }
            }
            result.add("+OK maildrop has " + listOfAllMails.size() + " messages");
        } else {
            result.add("-ERR no authentication");
        }
        return result;
    }
}
