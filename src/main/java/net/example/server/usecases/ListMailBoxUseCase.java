package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.MailEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListMailBoxUseCase {

    private final MailBoxRepository mailBoxRepository;
    private final Pop3SessionContext sessionContext;


    public ListMailBoxUseCase(Pop3SessionContext sessionContext, MailBoxRepository mailBoxRepository) {
        this.mailBoxRepository = mailBoxRepository;
        this.sessionContext = sessionContext;
    }

    public List<String> execute(Integer limit) {
        System.out.println("[" + sessionContext.getClientIP() + "] " + "execute ListMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();
        if (sessionContext.isAuthenticated()) {
            List<MailEntity> list = mailBoxRepository.list().stream()
                    .filter(mailEntity -> !mailEntity.isDeleted()).collect(Collectors.toList());

            if (!list.isEmpty()) {
                int countOfMessage = list.size();

                List<Integer> sizeOfMessage = list.stream()
                        .map(
                                mailEntity -> Stream.of(
                                        mailEntity.getSubject(),
                                        mailEntity.getFrom(),
                                        mailEntity.getTo(),
                                        mailEntity.getPayload())
                                        .map(i -> i.getBytes().length).reduce(0, Integer::sum))
                        .collect(Collectors.toList());


                int allCountOfBites = sizeOfMessage.stream().mapToInt(Integer::intValue).sum();

                Stream.of("+OK " + countOfMessage + " " + allCountOfBites).forEachOrdered(result::add);
                if (limit > list.size()) {
                    limit = list.size();
                }

                if (limit == 0) {
                    for (int i = 0; i < sizeOfMessage.size(); i++) {
                        result.add(list.get(i).getId() + " " + sizeOfMessage.get(i));
                    }
                } else {
                    for (int i = 0; i < limit; i++) {
                        result.add(list.get(i).getId() + " " + sizeOfMessage.get(i));
                    }
                }

            } else {
                result.add("-ERR no messages");
            }
        } else {
            result.add("-ERR no authentication");
        }
        return result;
    }
}
