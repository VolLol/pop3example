package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.MailEntity;

import java.util.ArrayList;
import java.util.List;

public class StatMailBoxUseCase {

    private final MailBoxRepository mailBoxRepository;
    private final Pop3SessionContext sessionContext;

    public StatMailBoxUseCase(Pop3SessionContext sessionContext, MailBoxRepository mailBoxRepository) {
        this.mailBoxRepository = mailBoxRepository;
        this.sessionContext = sessionContext;
    }

    public List<String> execute() {
        System.out.println("[" + sessionContext.getClientIP() + "] " + "execute StatMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();
        if (sessionContext.isAuthenticated()) {

            List<MailEntity> list = mailBoxRepository.list();

            int countOfMails = 0;
            int intCount;
            int allCountOfBites = 0;
            for (MailEntity mailEntity : list) {
                if (!mailEntity.isDeleted()) {
                    intCount = mailEntity.getSubject().getBytes().length;
                    intCount = intCount + mailEntity.getFrom().getBytes().length;
                    intCount = intCount + mailEntity.getTo().getBytes().length;
                    intCount = intCount + mailEntity.getPayload().getBytes().length;
                    allCountOfBites = allCountOfBites + intCount;
                    countOfMails++;
                }
            }
            result.add("+OK " + countOfMails + " " + allCountOfBites);
        } else {
            result.add("-ERR user not authenticated");
        }
        return result;
    }
}
