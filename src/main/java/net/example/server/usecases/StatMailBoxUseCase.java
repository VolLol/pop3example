package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.MailEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

            List<MailEntity> list = mailBoxRepository.list().stream()
                    .filter(mailEntity -> !mailEntity.isDeleted())
                    .collect(Collectors.toList());

            Integer allCountOfBites = list.stream()
                    .map(
                            mailEntity -> Stream.of(
                                    mailEntity.getSubject(),
                                    mailEntity.getFrom(),
                                    mailEntity.getTo(),
                                    mailEntity.getPayload())
                                    .map(i -> i.getBytes().length)
                                    .reduce(0, Integer::sum))
                    .reduce(0, Integer::sum);

            int countOfMails = list.size();

            result.add("+OK " + countOfMails + " " + allCountOfBites);
        } else {
            result.add("-ERR user not authenticated");
        }
        return result;
    }
}
