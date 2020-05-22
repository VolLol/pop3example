package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.MailEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RetrMailBoxUseCase {

    private final MailBoxRepository mailBoxRepository;
    private final Pop3SessionContext sessionContext;


    public RetrMailBoxUseCase(Pop3SessionContext sessionContext, MailBoxRepository mailBoxRepository) {
        this.mailBoxRepository = mailBoxRepository;
        this.sessionContext = sessionContext;
    }

    public List<String> execute(int mailIndex) {
        System.out.println("[" + sessionContext.getClientIP() + "] " + " execute RetrMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();
        if (sessionContext.isAuthenticated()) {
            if ((mailIndex < mailBoxRepository.list().size()) && (mailIndex >= 1)) {
                result.add("+OK message follows");
                MailEntity mailEntity = mailBoxRepository.getById(mailIndex);
                result.add(mailEntity.getSubject());
                result.add(mailEntity.getFrom());
                result.add(mailEntity.getTo());

                List<String> bodyOfTheMail = Arrays.asList(mailEntity.getPayload().split("\n"));
                result.addAll(bodyOfTheMail);

            } else {
                result.add("-ERR no such message");
            }
        } else {
            result.add("-ERR no authentication");
        }
        return result;
    }
}
