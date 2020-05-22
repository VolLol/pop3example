package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.MailEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopMailBoxUseCase {

    private final MailBoxRepository mailBoxRepository;
    private final Pop3SessionContext sessionContext;


    public TopMailBoxUseCase(Pop3SessionContext sessionContext, MailBoxRepository mailBoxRepository) {
        this.mailBoxRepository = mailBoxRepository;
        this.sessionContext = sessionContext;
    }

    public List<String> execute(int mailIndex, int countLines) {
        System.out.println("[" + sessionContext.getClientIP() + "] " + "execute TopMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();
        if (sessionContext.isAuthenticated()) {
            if (mailIndex > 0 && mailIndex < mailBoxRepository.list().size()) {
                result.add("+OK top of message follows");
                MailEntity mailEntity = mailBoxRepository.getById(mailIndex);
                List<String> mailList = new ArrayList<>();
                mailList.add(mailEntity.getSubject());
                mailList.add(mailEntity.getFrom());
                mailList.add(mailEntity.getTo());
                List<String> listOfStringsFromPayload = new ArrayList<>();
                if (countLines != 0) {
                    String stringOfPayload = mailEntity.getPayload();
                    listOfStringsFromPayload = Arrays.asList(stringOfPayload.split("\n"));
                    if (countLines > listOfStringsFromPayload.size()) {
                        countLines = listOfStringsFromPayload.size();
                    }
                    for (int i = 0; i < countLines; i++) {
                        mailList.add(listOfStringsFromPayload.get(i));
                    }
                }

                result.addAll(mailList);
            } else {
                result.add("-ERR no such message");
            }
        } else {
            result.add("-ERR no authentication");
        }
        return result;
    }
}