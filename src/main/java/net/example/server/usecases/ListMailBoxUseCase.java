package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.MailEntity;

import java.util.ArrayList;
import java.util.List;

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
            List<MailEntity> list = new ArrayList<>();

            for (MailEntity m : mailBoxRepository.list()) {
                if (!m.isDeleted()) {
                    list.add(m);
                }
            }
            if (!list.isEmpty()) {
                int countOfMessage = list.size();
                int intCount;
                int allCountOfBites = 0;
                List<Integer> sizeOfMessage = new ArrayList<>();
                for (MailEntity mailEntity : list) {
                    intCount = mailEntity.getSubject().getBytes().length;
                    intCount = intCount + mailEntity.getFrom().getBytes().length;
                    intCount = intCount + mailEntity.getTo().getBytes().length;
                    intCount = intCount + mailEntity.getPayload().getBytes().length;
                    allCountOfBites = allCountOfBites + intCount;
                    sizeOfMessage.add(intCount);
                }
                result.add("+OK " + countOfMessage + " " + allCountOfBites);
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
