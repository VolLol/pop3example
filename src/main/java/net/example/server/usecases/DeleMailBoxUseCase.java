package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.MailEntity;

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
        System.out.println("[" + sessionContext.getClientIP() + "] " + "execute DeleMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();
        if (sessionContext.isAuthenticated()) {
            if (mailIndex < mailBoxRepository.list().size() && mailIndex != 0) {
                MailEntity mailEntity = mailBoxRepository.get(mailIndex - 1);
                if (!mailEntity.isDeleted()) {
                    mailEntity.setDeleted(true);
                    result.add("+Ok message " + mailIndex + " is deleted");
                } else {
                    result.add("-ERR message " + mailIndex + " already deleted");
                }
            } else {
                result.add("-ERR not correct index");
            }
        } else {
            result.add("-ERR no authentication");
        }

        return result;
    }
}
